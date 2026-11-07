package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.klef.soa.dto.JournalRequest;


import com.klef.soa.entity.Journal;
import com.klef.soa.service.JournalService;

@RestController
@RequestMapping("/journal")
public class JournalController {
	@Autowired
	private JournalService service;
	@GetMapping("/")
	public String demo(){
		return "Spring boot project";
	}
	// Add journal
	@PostMapping("/add")
	public ResponseEntity<Journal> addJournal(@RequestBody JournalRequest request)
	{
	    Journal j = new Journal();

	    j.setName(request.getName());
	    j.setType(request.getType());
	    j.setLocation(request.getLocation());
	    j.setStatus(request.isStatus());

	    Journal journal = service.addJournal(j);

	    return ResponseEntity.status(HttpStatus.CREATED).body(journal);
	}
	// Display All 
	  @GetMapping("/displayall")
	  public ResponseEntity<List<Journal>> displayAlljournals()
	  {
	    List<Journal> journals = service.displayAllJournals();
	    return ResponseEntity.status(200).body(journals);
	  }
	// Display Journal By ID
	  @GetMapping("/display")
	  public ResponseEntity<?> displayDJournalById(@RequestParam Long id)
	  {
	    Journal journal = service.displayJournalById(id);

	    if (journal != null)
	    {
	      return ResponseEntity.status(200).body(journal);
	    }
	    else
	    {
	      return ResponseEntity.status(404).body("Journal ID Not Found");
	    }
	  }
	// Update Journal
	  @PutMapping("/update")
	  public ResponseEntity<?> updateJournal(@RequestBody Journal journal)
	  {
	      Journal j = service.updateJournal(journal);

	      if (j != null)
	      {
	          return ResponseEntity.ok(j);
	      }
	      else
	      {
	          return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                  .body("Journal ID Not Found");
	      }
	  }

	  // Delete Journal By ID
	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<String> deleteJournalById(@PathVariable Long id)
	  {
	      String message = service.deleteJournalById(id);
	      return new ResponseEntity<>(message, HttpStatus.OK);
	  }

	  // Display Journals By Name
	  @GetMapping("/displaybyname/{name}")
	  public ResponseEntity<List<Journal>> displayJournalsByName(@PathVariable String name)
	  {
	      List<Journal> journals = service.displayJournalsByName(name);
	      return ResponseEntity.status(HttpStatus.OK).body(journals);
	  }

	  // Display Journals By Type
	  @GetMapping("/displaybytype/{type}")
	  public ResponseEntity<List<Journal>> displayJournalsByType(@PathVariable String type)
	  {
	      List<Journal> journals = service.displayJournalsByType(type);
	      return ResponseEntity.status(HttpStatus.OK).body(journals);
	  }
	  

}
