package com.example.notes.data.repository

import com.example.notes.data.storage.models.NoteCache
import com.example.notes.data.storage.models.NoteCloud
import com.example.notes.domain.models.NoteDomain

interface MapperNoteDB {

    fun mapDomainToCache(noteDomain: NoteDomain): NoteCache
    fun mapDomainToCloud(noteDomain: NoteDomain): NoteCloud
    fun mapCacheToDomain(noteCache: NoteCache): NoteDomain
    fun mapCloudToDomain(noteCloud: NoteCloud): NoteDomain

    class Base : MapperNoteDB {

        override fun mapDomainToCache(noteDomain: NoteDomain) =
            NoteCache(
                id = noteDomain.id,
                firebaseId = noteDomain.firebaseId,
                name = noteDomain.name,
                text = noteDomain.text,
                performDate = noteDomain.performDate,
                performed = noteDomain.performed
            )

        override fun mapDomainToCloud(noteDomain: NoteDomain) =
            NoteCloud(
                id = noteDomain.id,
                firebaseId = noteDomain.firebaseId,
                name = noteDomain.name,
                text = noteDomain.text,
                performDate = noteDomain.performDate,
                performed = noteDomain.performed
            )

        override fun mapCacheToDomain(noteCache: NoteCache) =
            NoteDomain(
                id = noteCache.id,
                firebaseId = noteCache.firebaseId,
                name = noteCache.name,
                text = noteCache.text,
                performDate = noteCache.performDate,
                performed = noteCache.performed
            )

        override fun mapCloudToDomain(noteCloud: NoteCloud) =
            NoteDomain(
                id = noteCloud.id,
                firebaseId = noteCloud.firebaseId,
                name = noteCloud.name,
                text = noteCloud.text,
                performDate = noteCloud.performDate,
                performed = noteCloud.performed
            )
    }
}