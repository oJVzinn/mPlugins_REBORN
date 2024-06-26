package tk.slicecollections.maxteer.database.cache.types;

import tk.slicecollections.maxteer.database.Database;
import tk.slicecollections.maxteer.database.cache.Data;
import tk.slicecollections.maxteer.database.cache.DataCollection;
import tk.slicecollections.maxteer.database.cache.collections.CoinsGenericInformation;
import tk.slicecollections.maxteer.database.cache.collections.MurderStatsInformation;
import tk.slicecollections.maxteer.database.enuns.DataTypes;
import tk.slicecollections.maxteer.database.types.MySQL;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MurderCache extends Data {

    public MurderCache(String playerKey, boolean setupTables, boolean load) {
        super("mCoreMurder", playerKey);
        this.loadValue = load;
        if (setupTables) setupTables();

        setupCollections(MurderStatsInformation.class);
    }

    @Override
    public Data setupTables() {
        DataTypes type = Database.getInstance().getType();
        if (type.equals(DataTypes.MYSQL)) {
            ((MySQL) Database.getInstance()).setupTable(this.tableName, "`name` VARCHAR(32) PRIMARY KEY NOT NULL, ",
                    "`stats` TEXT, ",
                    "`coins` DOUBLE, ",
                    "`lastmap` LONG, ",
                    "`cosmetics` VARCHAR(255), ",
                    "`selected` VARCHAR(255)");
        }

        return this;
    }

    @SafeVarargs
    @Override
    public final void setupCollections(Class<? extends DataCollection>... collections) {
        Arrays.stream(collections).forEach(clazz -> {
            try {
                Constructor<? extends DataCollection> constructor = clazz.getConstructor(String.class);
                DataCollection collectionCache = constructor.newInstance(this.playerKey);
                if (!loadValue) collectionCache.setupColumn();
                registerNewCollection(collectionCache);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });

        registerNewCollection(new CoinsGenericInformation(this.playerKey, "coins", "mCoreMurder")); //Coluna generica de coins
        
        if (this.playerKey.isEmpty()) {
            return;
        }

        if (loadValue) loadValueCollections(false);
    }

    @Override
    public void loadValueCollections(boolean asyncTask) {
        Thread task = new Thread(() -> {
            DataTypes type = Database.getInstance().getType();
            Map<String, Object> collectionsValue;
            if (type.equals(DataTypes.MYSQL)) {
                MySQL mySQL = ((MySQL) Database.getInstance());
                List<String> columns = listCollections().stream().map(DataCollection::getColumnName).collect(Collectors.toList());
                collectionsValue = mySQL.getValueColumn(this.tableName, "name = '" + this.playerKey + "'", columns);
                if (collectionsValue.isEmpty()) {
                    mySQL.insertValue(this.tableName, "name", this.playerKey);
                }
            } else {
                collectionsValue = null;
            }

            listCollections().forEach(collectionCache -> {
                if (collectionsValue != null && !collectionsValue.isEmpty() && collectionsValue.get(collectionCache.getColumnName()) != null) {
                    collectionCache.updateValue(collectionsValue.get(collectionCache.getColumnName()));
                    return;
                }

                collectionCache.updateValue(collectionCache.getDefaultValue());
            });
        });


        if (asyncTask) {
            task.start();
        } else {
            task.run();
        }
    }

    @Override
    public void saveValueCollections(boolean asyncTask) {
        defaultSave(asyncTask);
    }

}
