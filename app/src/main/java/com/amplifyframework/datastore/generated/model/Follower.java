package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
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

/** This is an auto generated class representing the Follower type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Followers")
@Index(name = "byUser", fields = {"userID"})
public final class Follower implements Model {
  public static final QueryField ID = field("Follower", "id");
  public static final QueryField USER = field("Follower", "userID");
  public static final QueryField USER_FOLLOWER = field("Follower", "userFollower");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="User") @BelongsTo(targetName = "userID", type = User.class) User user;
  private final @ModelField(targetType="String") String userFollower;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public User getUser() {
      return user;
  }
  
  public String getUserFollower() {
      return userFollower;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Follower(String id, User user, String userFollower) {
    this.id = id;
    this.user = user;
    this.userFollower = userFollower;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Follower follower = (Follower) obj;
      return ObjectsCompat.equals(getId(), follower.getId()) &&
              ObjectsCompat.equals(getUser(), follower.getUser()) &&
              ObjectsCompat.equals(getUserFollower(), follower.getUserFollower()) &&
              ObjectsCompat.equals(getCreatedAt(), follower.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), follower.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUser())
      .append(getUserFollower())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Follower {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("user=" + String.valueOf(getUser()) + ", ")
      .append("userFollower=" + String.valueOf(getUserFollower()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
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
  public static Follower justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Follower(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      user,
      userFollower);
  }
  public interface BuildStep {
    Follower build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep user(User user);
    BuildStep userFollower(String userFollower);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private User user;
    private String userFollower;
    @Override
     public Follower build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Follower(
          id,
          user,
          userFollower);
    }
    
    @Override
     public BuildStep user(User user) {
        this.user = user;
        return this;
    }
    
    @Override
     public BuildStep userFollower(String userFollower) {
        this.userFollower = userFollower;
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
    private CopyOfBuilder(String id, User user, String userFollower) {
      super.id(id);
      super.user(user)
        .userFollower(userFollower);
    }
    
    @Override
     public CopyOfBuilder user(User user) {
      return (CopyOfBuilder) super.user(user);
    }
    
    @Override
     public CopyOfBuilder userFollower(String userFollower) {
      return (CopyOfBuilder) super.userFollower(userFollower);
    }
  }
  
}
