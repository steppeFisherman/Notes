package com.example.notes.data.storage.firebase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

interface FirebaseInstance {

    fun noteCollectionRef(): CollectionReference

    class Base : FirebaseInstance {
        override fun noteCollectionRef(): CollectionReference =
            FirebaseFirestore.getInstance().collection("Notes")
    }
}