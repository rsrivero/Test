package com.cash.online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.cash.online.dto.LoanDTO;
import com.cash.online.dto.LoanResponse;
import com.cash.online.dto.Paging;
import com.cash.online.model.Loan;
import com.cash.online.model.User;
import com.cash.online.repository.LoanRepository;
import com.cash.online.repository.UserRepository;

@RestController
@RequestMapping("/")
public class Controller {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LoanRepository loanRepository;


	@GetMapping("/users/{id}")
	  public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
	    Optional<User> data = userRepository.findById(id);

	    if (data.isPresent()) {
	      return new ResponseEntity<>(data.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/users")
	  public ResponseEntity<User> createUser(@RequestBody User user) {
	    try {
	    	User _user = userRepository.save(new User(user.getFirstName(), user.getLastName(), user.getEmail()));
	      return new ResponseEntity<>(_user, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/loans")
	public ResponseEntity<LoanResponse> getLoans(@RequestParam("page") int page, 
			  							         @RequestParam("size") int size,
			  							         @RequestParam("user_id") Optional<Long>	id) {
		Pageable pagination = PageRequest.of(page, size, Sort.by("id"));

		Page<Loan> data;
		if (id.isPresent()) {
			data = loanRepository.findByUserId(id.get(), pagination);
		}else{
			data = loanRepository.findAll(pagination);
		}
		
	    return new ResponseEntity<>(convertToResponse(data, page, size), HttpStatus.OK);
	}
	
	private LoanResponse convertToResponse(Page<Loan> data, Integer page, Integer size){
		
		LoanResponse response = new LoanResponse();
		
		List<LoanDTO> list = new ArrayList<LoanDTO>();
		
		for (Loan loan : data.getContent()) {
			LoanDTO dto = new LoanDTO();
			dto.setId(loan.getId());
			dto.setTotal(loan.getTotal());
			dto.setUserId(loan.getUser().getId());
			list.add(dto);
		}
		Paging paging = new Paging();
		paging.setTotal(data.getTotalElements());
		paging.setPage(page);
		paging.setSize(size);
		
		response.setPaging(paging);
		response.setItems(list);
		return response;
	}
}
