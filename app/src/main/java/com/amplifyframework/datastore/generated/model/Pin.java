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

/** This is an auto generated class representing the Pin type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Pins")
@Index(name = "byUser", fields = {"userID","locatName"})
public final class Pin implements Model {
  public static final QueryField ID = field("Pin", "id");
  public static final QueryField USER_ID = field("Pin", "userID");
  public static final QueryField TITLE = field("Pin", "title");
  public static final QueryField BODY = field("Pin", "body");
  public static final QueryField IS_PRIVATE = field("Pin", "isPrivate");
  public static final QueryField PIN_IMG = field("Pin", "pinImg");
  public static final QueryField LAT = field("Pin", "lat");
  public static final QueryField LON = field("Pin", "lon");
  public static final QueryField LOCAT_NAME = field("Pin", "locatName");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String userID;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String") String body;
  private final @ModelField(targetType="Boolean") Boolean isPrivate;
  private final @ModelField(targetType="String") String pinImg;
  private final @ModelField(targetType="Float") Double lat;
  private final @ModelField(targetType="Float") Double lon;
  private final @ModelField(targetType="String") String locatName;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getUserId() {
      return userID;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getBody() {
      return body;
  }
  
  public Boolean getIsPrivate() {
      return isPrivate;
  }
  
  public String getPinImg() {
      return pinImg;
  }
  
  public Double getLat() {
      return lat;
  }
  
  public Double getLon() {
      return lon;
  }
  
  public String getLocatName() {
      return locatName;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Pin(String id, String userID, String title, String body, Boolean isPrivate, String pinImg, Double lat, Double lon, String locatName) {
    this.id = id;
    this.userID = userID;
    this.title = title;
    this.body = body;
    this.isPrivate = isPrivate;
    this.pinImg = pinImg;
    this.lat = lat;
    this.lon = lon;
    this.locatName = locatName;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Pin pin = (Pin) obj;
      return ObjectsCompat.equals(getId(), pin.getId()) &&
              ObjectsCompat.equals(getUserId(), pin.getUserId()) &&
              ObjectsCompat.equals(getTitle(), pin.getTitle()) &&
              ObjectsCompat.equals(getBody(), pin.getBody()) &&
              ObjectsCompat.equals(getIsPrivate(), pin.getIsPrivate()) &&
              ObjectsCompat.equals(getPinImg(), pin.getPinImg()) &&
              ObjectsCompat.equals(getLat(), pin.getLat()) &&
              ObjectsCompat.equals(getLon(), pin.getLon()) &&
              ObjectsCompat.equals(getLocatName(), pin.getLocatName()) &&
              ObjectsCompat.equals(getCreatedAt(), pin.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), pin.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUserId())
      .append(getTitle())
      .append(getBody())
      .append(getIsPrivate())
      .append(getPinImg())
      .append(getLat())
      .append(getLon())
      .append(getLocatName())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Pin {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("userID=" + String.valueOf(getUserId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("body=" + String.valueOf(getBody()) + ", ")
      .append("isPrivate=" + String.valueOf(getIsPrivate()) + ", ")
      .append("pinImg=" + String.valueOf(getPinImg()) + ", ")
      .append("lat=" + String.valueOf(getLat()) + ", ")
      .append("lon=" + String.valueOf(getLon()) + ", ")
      .append("locatName=" + String.valueOf(getLocatName()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static UserIdStep builder() {
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
  public static Pin justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Pin(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      userID,
      title,
      body,
      isPrivate,
      pinImg,
      lat,
      lon,
      locatName);
  }
  public interface UserIdStep {
    TitleStep userId(String userId);
  }
  

  public interface TitleStep {
    BuildStep title(String title);
  }
  

  public interface BuildStep {
    Pin build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep body(String body);
    BuildStep isPrivate(Boolean isPrivate);
    BuildStep pinImg(String pinImg);
    BuildStep lat(Double lat);
    BuildStep lon(Double lon);
    BuildStep locatName(String locatName);
  }
  

  public static class Builder implements UserIdStep, TitleStep, BuildStep {
    private String id;
    private String userID;
    private String title;
    private String body;
    private Boolean isPrivate;
    private String pinImg;
    private Double lat;
    private Double lon;
    private String locatName;
    @Override
     public Pin build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Pin(
          id,
          userID,
          title,
          body,
          isPrivate,
          pinImg,
          lat,
          lon,
          locatName);
    }
    
    @Override
     public TitleStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.userID = userId;
        return this;
    }
    
    @Override
     public BuildStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public BuildStep body(String body) {
        this.body = body;
        return this;
    }
    
    @Override
     public BuildStep isPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
        return this;
    }
    
    @Override
     public BuildStep pinImg(String pinImg) {
        this.pinImg = pinImg;
        return this;
    }
    
    @Override
     public BuildStep lat(Double lat) {
        this.lat = lat;
        return this;
    }
    
    @Override
     public BuildStep lon(Double lon) {
        this.lon = lon;
        return this;
    }
    
    @Override
     public BuildStep locatName(String locatName) {
        this.locatName = locatName;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String userId, String title, String body, Boolean isPrivate, String pinImg, Double lat, Double lon, String locatName) {
      super.id(id);
      super.userId(userId)
        .title(title)
        .body(body)
        .isPrivate(isPrivate)
        .pinImg(pinImg)
        .lat(lat)
        .lon(lon)
        .locatName(locatName);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder body(String body) {
      return (CopyOfBuilder) super.body(body);
    }
    
    @Override
     public CopyOfBuilder isPrivate(Boolean isPrivate) {
      return (CopyOfBuilder) super.isPrivate(isPrivate);
    }
    
    @Override
     public CopyOfBuilder pinImg(String pinImg) {
      return (CopyOfBuilder) super.pinImg(pinImg);
    }
    
    @Override
     public CopyOfBuilder lat(Double lat) {
      return (CopyOfBuilder) super.lat(lat);
    }
    
    @Override
     public CopyOfBuilder lon(Double lon) {
      return (CopyOfBuilder) super.lon(lon);
    }
    
    @Override
     public CopyOfBuilder locatName(String locatName) {
      return (CopyOfBuilder) super.locatName(locatName);
    }
  }
  
}
