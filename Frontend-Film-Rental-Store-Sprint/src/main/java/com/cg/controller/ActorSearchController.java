package com.cg.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cg.service.ActorSearchService;
import com.cg.model.Actor;
import com.cg.model.ActorSearchModel;
 
@Controller
public class ActorSearchController {
 
	@Autowired
	private ActorSearchService actorSearchService;
 
 
	@GetMapping("/actorSearch")
	public String searchActors(@ModelAttribute ActorSearchModel searchModel, Model model) {
		try {
			model.addAttribute("searchModel", searchModel);
 
			switch (searchModel.getSearchType()) {
 
			case "firstName":
				model.addAttribute("results", actorSearchService.searchByFirstName(searchModel.getSearchTerm()));
				model.addAttribute("searchType", "list");
				break;
			case "lastName":
				model.addAttribute("results", actorSearchService.searchByLastName(searchModel.getSearchTerm()));
				model.addAttribute("searchType", "list");
				break;
 
			case "id":
				try {
					Integer actorId = Integer.parseInt(searchModel.getSearchTerm());
					Actor actor = actorSearchService.searchFilmsByActorId(actorId);
					System.out.println("Actor retrieved: " + actor); // Add logging
					System.out.println("Films: " + (actor != null ? actor.getFilms() : "null")); // Add logging
					model.addAttribute("actor", actor);
					model.addAttribute("searchType", "films");
				} catch (NumberFormatException e) {
					model.addAttribute("error", "Invalid Actor ID format. Please enter a valid number.");
					return "actorSearch";
				}
				break;
				// Other cases remain the same...
			}
		} catch (Exception e) {
			e.printStackTrace(); // Add detailed error logging
			model.addAttribute("error", "Error performing search: " + e.getMessage());
		}
		return "actorSearch";
	}
 
}
