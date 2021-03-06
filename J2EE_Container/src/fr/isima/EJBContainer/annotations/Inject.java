/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 *  Ajout d'annotation
 */
package fr.isima.EJBContainer.annotations;
import java.lang.annotation.*; 

/** Annotation d'injection
 * 
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 */
//Interdit l'usage de l'annotation sur autre chose que des champs
@Target({ElementType.FIELD,ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {	
}
