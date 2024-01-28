package com.bananaapps.bananamusic.persistence.music;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bananaapps.bananamusic.domain.music.SongCategory;
import com.bananaapps.bananamusic.domain.music.Song;

public class JpaSongRepository implements SongRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Song findOne(Long id) {
        Song song = em.find(Song.class, id);
        return song;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Collection<Song> findAll() {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Collection<Song> findByKeyword(String keyword) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public long count() {
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Collection<Song> findByArtistContainingOrTitleContainingAllIgnoreCase(String artist, String title) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Collection<Song> findBySongCategory(SongCategory category) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Song save(Song song) {
        em.persist(song);
        return song;
    }

    @Override
    public void delete(Song song) {
        em.remove(song);
    }
}
