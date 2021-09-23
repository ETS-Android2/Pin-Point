package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.util.Immutable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/** 
 *  Contains the set of model classes that implement {@link Model}
 * interface.
 */

public final class AmplifyModelProvider implements ModelProvider {
<<<<<<< HEAD
  private static final String AMPLIFY_MODEL_VERSION = "90ba287a419517b31c923230e3489f8b";
=======
  private static final String AMPLIFY_MODEL_VERSION = "4057bcdce9a310796e6cd5070cc5b11b";
>>>>>>> 687ed4180259537c2748835561b52a4908b91bc9
  private static AmplifyModelProvider amplifyGeneratedModelInstance;
  private AmplifyModelProvider() {
    
  }
  
  public static AmplifyModelProvider getInstance() {
    if (amplifyGeneratedModelInstance == null) {
      amplifyGeneratedModelInstance = new AmplifyModelProvider();
    }
    return amplifyGeneratedModelInstance;
  }
  
  /** 
   * Get a set of the model classes.
   * 
   * @return a set of the model classes.
   */
  @Override
   public Set<Class<? extends Model>> models() {
    final Set<Class<? extends Model>> modifiableSet = new HashSet<>(
<<<<<<< HEAD
          Arrays.<Class<? extends Model>>asList(Task.class, Note.class)
=======
          Arrays.<Class<? extends Model>>asList(User.class, Pin.class)
>>>>>>> 687ed4180259537c2748835561b52a4908b91bc9
        );
    
        return Immutable.of(modifiableSet);
        
  }
  
  /** 
   * Get the version of the models.
   * 
   * @return the version string of the models.
   */
  @Override
   public String version() {
    return AMPLIFY_MODEL_VERSION;
  }
}
