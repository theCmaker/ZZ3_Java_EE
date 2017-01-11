/** ISIMA ZZ3 F2
 *  2016-17
 *  Injection de dépendances
 *  
 *  Ajout d'annotation de log
 */
package fr.isima.exercices.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author Pierre-Loup Pissavy, Pierre Chevalier
 *
 */
@Target({ElementType.METHOD})
public @interface Log {

}