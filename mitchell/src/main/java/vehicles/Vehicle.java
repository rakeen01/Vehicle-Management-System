package vehicles;

/*
Name: Md Rakeen Murtaza
The following program is a Vehicle Database service with CRUD operations,
which can be used by clients by interacting with this web-application.

The following entity represents a company Vehicle

The JPA entity class with the class name and field names are identical to 
column names of the table vehicle in the database
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@Entity
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private static final int MIN_RANGE = 1950;
    private static final int MAX_RANGE = 2050;
    
    // Ensure year is non-empty, with min 1950 and max 2050
    @Column(name = "year", nullable = false)
    @NotNull(message= "Year should not be empty!")
    @Range(min =MIN_RANGE,max=MAX_RANGE)
    private Integer year;
    
    // Ensure Vehicle make is non-empty
    @Size(min=1)
    @Column(unique=true, nullable=false)
    private String make;
    
    // Ensure Vehicle model is non-empty
    @Size(min=1)
    @Column(unique=true, nullable=false)
    private String model;

    // No-arg constructor
    public Vehicle() {
    }

    // Overloaded constructor
    public Vehicle(Integer inputYear, String inputMake, String inputModel) {
        this.year = inputYear;
        this.make = inputMake;
        this.model = inputModel;
    }
    
    // Getter for ID
    public Integer getId() {
        return id;
    }

    // Setters and getters for Vehicle Year
    public void setYear(Integer inputYear) {
        this.year = inputYear;
    }
    
    public Integer getYear() {
        return year;
    }

    // Setters and getters for Vehicle Make
    public void setMake(String inputMake) {
        this.make = inputMake;
    }
    
    public String getMake() {
        return make;
    }

    // Setters and getters for Vehicle Model
    public void setModel(String inputModel) {
        this.model = inputModel;
    }
    
    public String getModel() {
        return model;
    }
}
