/**
 */
package com.justeat.mickeydb.mickeyLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Drop Index Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.justeat.mickeydb.mickeyLang.DropIndexStatement#isIfExists <em>If Exists</em>}</li>
 *   <li>{@link com.justeat.mickeydb.mickeyLang.DropIndexStatement#getIndex <em>Index</em>}</li>
 * </ul>
 *
 * @see com.justeat.mickeydb.mickeyLang.MickeyLangPackage#getDropIndexStatement()
 * @model
 * @generated
 */
public interface DropIndexStatement extends DDLStatement
{
  /**
   * Returns the value of the '<em><b>If Exists</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>If Exists</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>If Exists</em>' attribute.
   * @see #setIfExists(boolean)
   * @see com.justeat.mickeydb.mickeyLang.MickeyLangPackage#getDropIndexStatement_IfExists()
   * @model
   * @generated
   */
  boolean isIfExists();

  /**
   * Sets the value of the '{@link com.justeat.mickeydb.mickeyLang.DropIndexStatement#isIfExists <em>If Exists</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>If Exists</em>' attribute.
   * @see #isIfExists()
   * @generated
   */
  void setIfExists(boolean value);

  /**
   * Returns the value of the '<em><b>Index</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Index</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Index</em>' reference.
   * @see #setIndex(CreateIndexStatement)
   * @see com.justeat.mickeydb.mickeyLang.MickeyLangPackage#getDropIndexStatement_Index()
   * @model
   * @generated
   */
  CreateIndexStatement getIndex();

  /**
   * Sets the value of the '{@link com.justeat.mickeydb.mickeyLang.DropIndexStatement#getIndex <em>Index</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Index</em>' reference.
   * @see #getIndex()
   * @generated
   */
  void setIndex(CreateIndexStatement value);

} // DropIndexStatement
