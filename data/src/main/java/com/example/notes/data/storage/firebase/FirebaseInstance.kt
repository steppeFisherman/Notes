package com.example.notes.data.storage.firebase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


interface FirebaseInstance {

  //  fun fireBaseInstance(): FirebaseFirestore
    fun noteCollectionReference(): CollectionReference

    class Base : FirebaseInstance {

    //    override fun fireBaseInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()

        override fun noteCollectionReference(): CollectionReference =
            FirebaseFirestore.getInstance().collection("Notes")
    }
}