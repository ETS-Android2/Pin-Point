package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class User implements Model {
  public static final QueryField ID = field("User", "id");
  public static final QueryField USER_NAME = field("User", "userName");
  public static final QueryField FIRST_NAME = field("User", "firstName");
  public static final QueryField LAST_NAME = field("User", "lastName");
  public static final QueryField BIO = field("User", "bio");
  public static final QueryField EMAIL = field("User", "email");
  public static final QueryField IMG = field("User", "img");
  public static final QueryField USER_FOLLOWING_ID = field("User", "userFollowingId");
  public static final QueryField USER_FOLLOWERS_ID = field("User", "userFollowersId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String userName;
  private final @ModelField(targetType="String") String firstName;
  private final @ModelField(targetType="String") String lastName;
  private final @ModelField(targetType="String") String bio;
  private final @ModelField(targetType="String") String email;
  private final @ModelField(targetType="String") String img;
  private final @ModelField(targetType="User") @HasMany(associatedWith = "userFollowingId", type = User.class) List<User> following = null;
  private final @ModelField(targetType="User") @HasMany(associatedWith = "userFollowersId", type = User.class) List<User> followers = null;
  private final @ModelField(targetType="Pin") @HasMany(associatedWith = "userID", type = Pin.class) List<Pin> Pins = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String userFollowingId;
  private final @ModelField(targetType="ID") String userFollowersId;
  public String getId() {
      return id;
  }
  
  public String getUserName() {
      return userName;
  }
  
  public String getFirstName() {
      return firstName;
  }
  
  public String getLastName() {
      return lastName;
  }
  
  public String getBio() {
      return bio;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getImg() {
      return img;
  }
  
  public List<User> getFollowing() {
      return following;
  }
  
  public List<User> getFollowers() {
      return followers;
  }
  
  public List<Pin> getPins() {
      return Pins;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getUserFollowingId() {
      return userFollowingId;
  }
  
  public String getUserFollowersId() {
      return userFollowersId;
  }
  
  private User(String id, String userName, String firstName, String lastName, String bio, String email, String img, String userFollowingId, String userFollowersId) {
    this.id = id;
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.bio = bio;
    this.email = email;
    this.img = img;
    this.userFollowingId = userFollowingId;
    this.userFollowersId = userFollowersId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getUserName(), user.getUserName()) &&
              ObjectsCompat.equals(getFirstName(), user.getFirstName()) &&
              ObjectsCompat.equals(getLastName(), user.getLastName()) &&
              ObjectsCompat.equals(getBio(), user.getBio()) &&
              ObjectsCompat.equals(getEmail(), user.getEmail()) &&
              ObjectsCompat.equals(getImg(), user.getImg()) &&
              ObjectsCompat.equals(getCreatedAt(), user.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), user.getUpdatedAt()) &&
              ObjectsCompat.equals(getUserFollowingId(), user.getUserFollowingId()) &&
              ObjectsCompat.equals(getUserFollowersId(), user.getUserFollowersId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUserName())
      .append(getFirstName())
      .append(getLastName())
      .append(getBio())
      .append(getEmail())
      .append(getImg())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getUserFollowingId())
      .append(getUserFollowersId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("userName=" + String.valueOf(getUserName()) + ", ")
      .append("firstName=" + String.valueOf(getFirstName()) + ", ")
      .append("lastName=" + String.valueOf(getLastName()) + ", ")
      .append("bio=" + String.valueOf(getBio()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("img=" + String.valueOf(getImg()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("userFollowingId=" + String.valueOf(getUserFollowingId()) + ", ")
      .append("userFollowersId=" + String.valueOf(getUserFollowersId()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
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
  public static User justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new User(
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
      userName,
      firstName,
      lastName,
      bio,
      email,
      img,
      userFollowingId,
      userFollowersId);
  }
  public interface BuildStep {
    User build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep userName(String userName);
    BuildStep firstName(String firstName);
    BuildStep lastName(String lastName);
    BuildStep bio(String bio);
    BuildStep email(String email);
    BuildStep img(String img);
    BuildStep userFollowingId(String userFollowingId);
    BuildStep userFollowersId(String userFollowersId);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String bio;
    private String email;
    private String img;
    private String userFollowingId;
    private String userFollowersId;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          userName,
          firstName,
          lastName,
          bio,
          email,
          img,
          userFollowingId,
          userFollowersId);
    }
    
    @Override
     public BuildStep userName(String userName) {
        this.userName = userName;
        return this;
    }
    
    @Override
     public BuildStep firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    
    @Override
     public BuildStep lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    
    @Override
     public BuildStep bio(String bio) {
        this.bio = bio;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep img(String img) {
        this.img = img;
        return this;
    }
    
    @Override
     public BuildStep userFollowingId(String userFollowingId) {
        this.userFollowingId = userFollowingId;
        return this;
    }
    
    @Override
     public BuildStep userFollowersId(String userFollowersId) {
        this.userFollowersId = userFollowersId;
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
    private CopyOfBuilder(String id, String userName, String firstName, String lastName, String bio, String email, String img, String userFollowingId, String userFollowersId) {
      super.id(id);
      super.userName(userName)
        .firstName(firstName)
        .lastName(lastName)
        .bio(bio)
        .email(email)
        .img(img)
        .userFollowingId(userFollowingId)
        .userFollowersId(userFollowersId);
    }
    
    @Override
     public CopyOfBuilder userName(String userName) {
      return (CopyOfBuilder) super.userName(userName);
    }
    
    @Override
     public CopyOfBuilder firstName(String firstName) {
      return (CopyOfBuilder) super.firstName(firstName);
    }
    
    @Override
     public CopyOfBuilder lastName(String lastName) {
      return (CopyOfBuilder) super.lastName(lastName);
    }
    
    @Override
     public CopyOfBuilder bio(String bio) {
      return (CopyOfBuilder) super.bio(bio);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder img(String img) {
      return (CopyOfBuilder) super.img(img);
    }
    
    @Override
     public CopyOfBuilder userFollowingId(String userFollowingId) {
      return (CopyOfBuilder) super.userFollowingId(userFollowingId);
    }
    
    @Override
     public CopyOfBuilder userFollowersId(String userFollowersId) {
      return (CopyOfBuilder) super.userFollowersId(userFollowersId);
    }
  }
  
}
