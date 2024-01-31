package com.bananaapps.bananamusic.persistence.music;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
        TypedQuery<Song> q = em.createQuery("SELECT s FROM Song s WHERE s.title LIKE CONCAT('%',:keyword,'%') OR s.artist LIKE CONCAT('%',:keyword,'%')", Song.class);
        q.setParameter("keyword", keyword);
        return q.getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public long count() {
        Query query = em.createQuery("SELECT count(*) FROM Song");
        return (long) query.getSingleResult();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Collection<Song> findByArtistContainingOrTitleContainingAllIgnoreCase(String artist, String title) {
        TypedQuery<Song> q = em.createQuery("SELECT s FROM Song s WHERE s.title LIKE CONCAT('%',:title,'%') OR s.artist LIKE CONCAT('%',:artist,'%')", Song.class);
        q.setParameter("artist", artist);
        q.setParameter("title", title);
        return q.getResultList();
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
