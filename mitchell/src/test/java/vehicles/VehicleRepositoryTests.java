/*
 * This class runs automation tests for some of the operations using JUnit
 */
package vehicles;

import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class VehicleRepositoryTests {
     
    @Autowired
    private VehicleRepository repo;
    
    // Test to check if new vehicle can be added and saved
    @Test
    @Rollback(false)
    public void testCreateVehicle(){
        Vehicle car = new Vehicle(2009, "Hyundai", "Sonata");
        Vehicle savedCar = repo.save(car);
        
        assertNotNull(savedCar);
    }
    
    // Test to check if search feature/filtering service works
    // This also checks read/retrieval (get method)
    @Test
    @Rollback(false)
    public void testFindVehicleByMake(){
        String make = "Honda";
        String val = "Hyundai"; // Dummy value to fail test otherwise
        List<Vehicle> cars = repo.search(make);
        ArrayList<Vehicle> carList = new ArrayList<>(cars);
        for(int i = 0; i < carList.size(); i++) {
            Vehicle temp = carList.get(i);
            val = temp.getMake();
            if(val == "Honda")
                break;
        }
        assertTrue(make.equals(val));
    }
}