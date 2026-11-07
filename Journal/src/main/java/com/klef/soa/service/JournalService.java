package com.klef.soa.service;

import java.util.List;

import com.klef.soa.entity.Journal;

public interface JournalService
{
    Journal addJournal(Journal j);

    Journal updateJournal(Journal j);

    List<Journal> displayAllJournals();

    Journal displayJournalById(Long id);

    String deleteJournalById(Long id);

    List<Journal> displayJournalsByName(String name);

    List<Journal> displayJournalsByType(String type);
}