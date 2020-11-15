package vehicles;

/*
In this class, we inject an instance of VehicleRepository via private field using @Autowired annotation.
At runtime, Spring Data JPA will generate a proxy instance of VehicleRepository and inject it to the 
instance of VehicleRepository class.

Although it seems to delegate all the calls to VehicleRepository anyway, we still have
this class for future extensibility.
 */

import vehicles.VehicleRepository;
import vehicles.Vehicle;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VehicleService {
    
    @Autowired
    private VehicleRepository repo;
     
    // In-memory persistence of Vehicles retrieved from DB
    // Allow search by make (filtering) if parameter is passed
    public List<Vehicle> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
     
    public void save(Vehicle car) {
        repo.save(car);
    }
     
    public Vehicle get(int id) {
        return repo.findById(id).get();
    }
     
    public void delete(int id) {
        repo.deleteById(id);
    }
}
