package hlushakovaM.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов.")
    private String name;

    @NotBlank(message = "Вид требуется")
    @Size(min = 2, max = 50, message = "Вид должен содержать от 2 до 50 символов.")
    private String species;

    @Min(value = 0, message = "Возраст должен быть не менее 0")
    @Max(value = 30, message = "Возраст должен быть не более 30 лет.")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    public Pet() {
    }

    public Pet(String name, String species, Integer age, House house) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.house = house;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", house=" + house +
                '}';
    }
}
