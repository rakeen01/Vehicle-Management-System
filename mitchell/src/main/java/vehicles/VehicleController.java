package vehicles;

/*
This Spring MVC Controller class handles requests from clients.

We inject an instance of the VehicleService class to this controller – 
Spring will automatically create one at runtime
 */

import vehicles.Vehicle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class VehicleController {
 
    @Autowired
    private VehicleService service;
     
    /* Handler Methods start */
    
    // Reads all the Vehicle instances in the database and displays them
    // Also implements a search by make feature if parameter is passed
    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        
        // Offloads work from a MySQL DB hosted on AWS into in-memory persistence 
        List<Vehicle> listVehicles = service.listAll(keyword);
        model.addAttribute("listVehicles", listVehicles);
        model.addAttribute("keyword", keyword); // To display the searched word upon refresh

        return "index";
    }
    
    // Create a new Vehicle in the database
    @RequestMapping("/new")
    public String showNewVehiclePage(Model model) {
        Vehicle car = new Vehicle();
        model.addAttribute("car", car);

        return "new_car";
    }
    
    // Save the created vehicle in the database
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveVehicle(@ModelAttribute("car") Vehicle car) {
        service.save(car);

        return "redirect:/"; // Return to homepage
    }
    
    // Update a Vehicle
    @RequestMapping("/update/{id}")
    public ModelAndView showUpdateVehiclePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("update_vehicle");
        Vehicle car = service.get(id);
        service.delete(id);
        mav.addObject("car", car);

        return mav;
    }
    
    // Delete a Vehicle
    @RequestMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/"; // Refresh homepage
    }

}