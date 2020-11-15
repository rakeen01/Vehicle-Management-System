package vehicles;

/*
This interface extends the JpaRepository interface from Spring Data JPA. 
JpaRepository defines standard CRUD methods, plus JPA-specific operations. 
We don’t have to write implementation code because Spring Data JPA will generate 
necessary code at runtime, in form of proxy instances.
*/


import java.util.List;
import vehicles.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    @Query("SELECT v FROM Vehicle v WHERE v.make LIKE %?1%")
    public List<Vehicle> search(String keyword);
    
}