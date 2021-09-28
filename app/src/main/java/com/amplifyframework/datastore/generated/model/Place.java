package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Place type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Places")
public final class Place implements Model {
  public static final QueryField ID = field("Place", "id");
  public static final QueryField ADDRESS = field("Place", "address");
  public static final QueryField CITY = field("Place", "city");
  public static final QueryField COUNTRY = field("Place", "country");
  public static final QueryField APPROXLAT = field("Place", "approxlat");
  public static final QueryField APPROXLON = field("Place", "approxlon");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String address;
  private final @ModelField(targetType="String") String city;
  private final @ModelField(targetType="String") String country;
  private final @ModelField(targetType="Float") Double approxlat;
  private final @ModelField(targetType="Float") Double approxlon;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getAddress() {
      return address;
  }
  
  public String getCity() {
      return city;
  }
  
  public String getCountry() {
      return country;
  }
  
  public Double getApproxlat() {
      return approxlat;
  }
  
  public Double getApproxlon() {
      return approxlon;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Place(String id, String address, String city, String country, Double approxlat, Double approxlon) {
    this.id = id;
    this.address = address;
    this.city = city;
    this.country = country;
    this.approxlat = approxlat;
    this.approxlon = approxlon;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Place place = (Place) obj;
      return ObjectsCompat.equals(getId(), place.getId()) &&
              ObjectsCompat.equals(getAddress(), place.getAddress()) &&
              ObjectsCompat.equals(getCity(), place.getCity()) &&
              ObjectsCompat.equals(getCountry(), place.getCountry()) &&
              ObjectsCompat.equals(getApproxlat(), place.getApproxlat()) &&
              ObjectsCompat.equals(getApproxlon(), place.getApproxlon()) &&
              ObjectsCompat.equals(getCreatedAt(), place.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), place.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getAddress())
      .append(getCity())
      .append(getCountry())
      .append(getApproxlat())
      .append(getApproxlon())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Place {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("address=" + String.valueOf(getAddress()) + ", ")
      .append("city=" + String.valueOf(getCity()) + ", ")
      .append("country=" + String.valueOf(getCountry()) + ", ")
      .append("approxlat=" + String.valueOf(getApproxlat()) + ", ")
      .append("approxlon=" + String.valueOf(getApproxlon()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static AddressStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Place justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Place(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      address,
      city,
      country,
      approxlat,
      approxlon);
  }
  public interface AddressStep {
    BuildStep address(String address);
  }
  

  public interface BuildStep {
    Place build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep city(String city);
    BuildStep country(String country);
    BuildStep approxlat(Double approxlat);
    BuildStep approxlon(Double approxlon);
  }
  

  public static class Builder implements AddressStep, BuildStep {
    private String id;
    private String address;
    private String city;
    private String country;
    private Double approxlat;
    private Double approxlon;
    @Override
     public Place build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Place(
          id,
          address,
          city,
          country,
          approxlat,
          approxlon);
    }
    
    @Override
     public BuildStep address(String address) {
        Objects.requireNonNull(address);
        this.address = address;
        return this;
    }
    
    @Override
     public BuildStep city(String city) {
        this.city = city;
        return this;
    }
    
    @Override
     public BuildStep country(String country) {
        this.country = country;
        return this;
    }
    
    @Override
     public BuildStep approxlat(Double approxlat) {
        this.approxlat = approxlat;
        return this;
    }
    
    @Override
     public BuildStep approxlon(Double approxlon) {
        this.approxlon = approxlon;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String address, String city, String country, Double approxlat, Double approxlon) {
      super.id(id);
      super.address(address)
        .city(city)
        .country(country)
        .approxlat(approxlat)
        .approxlon(approxlon);
    }
    
    @Override
     public CopyOfBuilder address(String address) {
      return (CopyOfBuilder) super.address(address);
    }
    
    @Override
     public CopyOfBuilder city(String city) {
      return (CopyOfBuilder) super.city(city);
    }
    
    @Override
     public CopyOfBuilder country(String country) {
      return (CopyOfBuilder) super.country(country);
    }
    
    @Override
     public CopyOfBuilder approxlat(Double approxlat) {
      return (CopyOfBuilder) super.approxlat(approxlat);
    }
    
    @Override
     public CopyOfBuilder approxlon(Double approxlon) {
      return (CopyOfBuilder) super.approxlon(approxlon);
    }
  }
  
}
