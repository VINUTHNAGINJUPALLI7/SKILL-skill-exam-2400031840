package com.klef.soa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.soa.entity.Journal;
import com.klef.soa.repository.JournalRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JournalRepositoryTest 
{
    @Autowired
    private JournalRepository repository;

    @Test
    void testFindAllJournals() 
    {
        List<Journal> journals = repository.findAll();
        assertNotNull(journals);
        assertFalse(journals.isEmpty());
        System.out.println("Total Journals: " + journals.size());
    }

    @Test
    void testFindJournalById() 
    {
        Optional<Journal> journal = repository.findById(1L);
        assertTrue(journal.isPresent());
        assertEquals(1L, journal.get().getJournalId());
        System.out.println("Journal ID: " + journal.get().getJournalId());
        System.out.println("Name: " + journal.get().getName());
    }

    @Test
    void testFindJournalByName() 
    {
        List<Journal> journals = repository.findByName("Java Programming");
        assertNotNull(journals);
        assertFalse(journals.isEmpty());
        assertEquals("Java Programming", journals.get(0).getName());
        System.out.println("Journal Name: " + journals.get(0).getName());
        System.out.println("Type: " + journals.get(0).getType());
    }

    @Test
    void testFindJournalByInvalidName() 
    {
        List<Journal> journals = repository.findByName("NotFoundJournal");
        assertTrue(journals.isEmpty());
    }

    @Test
    void testDatabaseConnection() 
    {
        long count = repository.count();
        assertTrue(count >= 0);
        System.out.println("Total Journals in Database: " + count);
    }

    @Test
    void testJournalDetails() 
    {
        Optional<Journal> journal = repository.findById(1L);
        assertTrue(journal.isPresent());

        Journal foundJournal = journal.get();

        assertNotNull(foundJournal.getName());
        assertNotNull(foundJournal.getType());
        assertNotNull(foundJournal.getLocation());

        System.out.println("Name: " + foundJournal.getName());
        System.out.println("Type: " + foundJournal.getType());
        System.out.println("Location: " + foundJournal.getLocation());
        System.out.println("Status: " + foundJournal.isStatus());
    }

    @Test
    void testJournalStatus() 
    {
        Optional<Journal> journal = repository.findById(1L);
        assertTrue(journal.isPresent());

        assertTrue(journal.get().isStatus());

        System.out.println("Status: " + journal.get().isStatus());
    }

    @Test
    void testTotalJournalCount() 
    {
        long count = repository.count();
        assertEquals(2L, count);
    }

    @Test
    void testJournalByType()
    {
        List<Journal> journals = repository.findByType("Technical");
        assertNotNull(journals);
        assertFalse(journals.isEmpty());
        assertEquals("Technical", journals.get(0).getType());
        System.out.println("Journal Type: " + journals.get(0).getType());
    }
}