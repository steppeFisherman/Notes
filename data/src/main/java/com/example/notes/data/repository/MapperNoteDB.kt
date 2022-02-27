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

        override fun mapDomainToCache(noteDomain: NoteDomain): NoteCache {
            return NoteCache(
                id = noteDomain.id,
                name = noteDomain.name,
                text = noteDomain.text
            )
        }

        override fun mapDomainToCloud(noteDomain: NoteDomain): NoteCloud {
            return NoteCloud(
                id = noteDomain.id,
                name = noteDomain.name,
                text = noteDomain.text
            )
        }

        override fun mapCacheToDomain(noteCache: NoteCache): NoteDomain {
            return NoteDomain(
                id = noteCache.id,
                name = noteCache.name,
                text = noteCache.text
            )
        }

        override fun mapCloudToDomain(noteCloud: NoteCloud): NoteDomain {
            return NoteDomain(
                id = noteCloud.id,
                name = noteCloud.name,
                text = noteCloud.text
            )
        }
    }
}