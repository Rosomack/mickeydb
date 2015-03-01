/**
 */
package com.justeat.mickeydb.mickeyLang;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group By Expressions</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.justeat.mickeydb.mickeyLang.GroupByExpressions#getGroupByExpressions <em>Group By Expressions</em>}</li>
 * </ul>
 *
 * @see com.justeat.mickeydb.mickeyLang.MickeyLangPackage#getGroupByExpressions()
 * @model
 * @generated
 */
public interface GroupByExpressions extends EObject
{
  /**
   * Returns the value of the '<em><b>Group By Expressions</b></em>' containment reference list.
   * The list contents are of type {@link com.justeat.mickeydb.mickeyLang.Expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group By Expressions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group By Expressions</em>' containment reference list.
   * @see com.justeat.mickeydb.mickeyLang.MickeyLangPackage#getGroupByExpressions_GroupByExpressions()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getGroupByExpressions();

} // GroupByExpressions
