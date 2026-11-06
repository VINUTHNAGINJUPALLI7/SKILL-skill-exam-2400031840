package com.klef.soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Journal;
import com.klef.soa.repository.JournalRepository;

@Service
public class JournalServiceImpl implements JournalService
{
    @Autowired
    private JournalRepository repo;

    @Override
    public Journal addJournal(Journal j)
    {
        return repo.save(j);
    }

    @Override
    public Journal updateJournal(Journal j)
    {
        Optional<Journal> optional = repo.findById(j.getJournalId());

        if(optional.isPresent())
        {
            Journal journal = optional.get();

            journal.setName(j.getName());
            journal.setType(j.getType());
            journal.setStatus(j.isStatus());
            journal.setLocation(j.getLocation());

            return repo.save(journal);
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<Journal> displayAllJournals()
    {
        return repo.findAll();
    }

    @Override
    public Journal displayJournalById(Long id)
    {
        return repo.findById(id).orElse(null);
    }

    @Override
    public String deleteJournalById(Long id)
    {
        boolean flag = repo.existsById(id);

        if(flag)
        {
            repo.deleteById(id);
            return "Journal Deleted Successfully";
        }
        else
        {
            return "Journal ID Not Found";
        }
    }

    @Override
    public List<Journal> displayJournalsByName(String name)
    {
        return repo.findByName(name);
    }

    @Override
    public List<Journal> displayJournalsByType(String type)
    {
        return repo.findByType(type);
    }
}