package com.phone.demo.realm;

import com.phone.demo.realm.entity.Dog;

import java.util.List;

import android.util.Log;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Phone on 2017/7/10.
 */

public class RealmHelper {

    private Realm realm;

    public RealmHelper() {
        realm = Realm.getDefaultInstance();
    }

    public void addDog(final Dog dog) {
        Log.i(Util.MYTAG, "addDog");
        //        //方式1
        //        realm.beginTransaction();
        //        realm.copyToRealm(dog);
        //        realm.commitTransaction();
        //方式2
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(dog);
            }
        });
    }

    public void deleteDog(final String id) {
        Log.i(Util.MYTAG, "deleteDog");
        //        //        //方式1
        //        Dog dog = realm
        //            .where(Dog.class)
        //            .equalTo("id", id)
        //            .findFirst();
        //        realm.beginTransaction();
        //        dog.deleteFromRealm();
        //        realm.commitTransaction();
        //方式2
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dog dog = realm
                    .where(Dog.class)
                    .equalTo("id", id)
                    .findFirst();
                dog.deleteFromRealm();
            }
        });
    }

    public void updateDog(String id, String newName) {
        Log.i(Util.MYTAG, "updateDog");
        Dog dog = realm
            .where(Dog.class)
            .equalTo("id", id)
            .findFirst();
        realm.beginTransaction();
        dog.setName(newName);
        realm.commitTransaction();
    }

    public List<Dog> queryAllDog() {
        Log.i(Util.MYTAG, "queryAllDog");
        RealmResults<Dog> dogs = realm
            .where(Dog.class)
            .findAll();
        dogs = dogs.sort("id", Sort.ASCENDING);
        return realm.copyFromRealm(dogs);
    }

    public Dog queryDogById(String id) {
        Log.i(Util.MYTAG, "queryDogById");
        Dog dog = realm
            .where(Dog.class)
            .equalTo("id", id)
            .findFirst();
        return dog;
    }

    public List<Dog> queryDogByAge(int age) {
        Log.i(Util.MYTAG, "queryDogByAge");
        final RealmResults<Dog> dogs = realm
            .where(Dog.class)
            .equalTo("age", age)
            .findAll();
        return realm.copyFromRealm(dogs);
    }

    public boolean isDogExist(String id) {
        Log.i(Util.MYTAG, "isDogExist");
        final Dog dog = realm
            .where(Dog.class)
            .equalTo("id", id)
            .findFirst();
        return dog == null ? false : true;
    }
}
