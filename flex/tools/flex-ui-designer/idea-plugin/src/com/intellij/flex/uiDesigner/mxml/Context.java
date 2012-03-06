package com.intellij.flex.uiDesigner.mxml;

import org.jetbrains.annotations.Nullable;

abstract class Context implements MxmlObjectReferenceProvider {
  protected StaticObjectContext backSibling;
  protected Scope parentScope;
  protected boolean cssRulesetDefined;

  protected MxmlObjectReference mxmlObjectReference;

  protected StaticInstanceReferenceInDeferredParentInstance staticInstanceReferenceInDeferredParentInstance;

  protected int id = -1;

  // state index => AddItems
  AddItems[] activeAddItems;

  protected String childrenType;

  Scope getParentScope() {
    return parentScope;
  }

  abstract void referenceInitialized();

  abstract Scope getScope();

  final boolean ownerIsDynamic() {
    return getScope().getOwner() != null;
  }

  int getId() {
    return id;
  }

  void setId(int value) {
    id = value;
  }

  public StaticInstanceReferenceInDeferredParentInstance getStaticInstanceReferenceInDeferredParentInstance() {
    return staticInstanceReferenceInDeferredParentInstance;
  }

  public void setStaticInstanceReferenceInDeferredParentInstance(
      @Nullable StaticInstanceReferenceInDeferredParentInstance value) {
    assert staticInstanceReferenceInDeferredParentInstance == null || value == null;
    staticInstanceReferenceInDeferredParentInstance = value;
  }

  public StaticObjectContext getBackSibling() {
    return backSibling;
  }

  public void setBackSibling(StaticObjectContext backSibling) {
    this.backSibling = backSibling;
  }

  @Nullable
  public String getChildrenType() {
    return childrenType;
  }

  public void setChildrenType(String value) {
    assert childrenType == null;
    childrenType = value;
  }

  public boolean isCssRulesetDefined() {
    return cssRulesetDefined;
  }
  
  public void markCssRulesetDefined() {
    cssRulesetDefined = true;
  }

  public void setMxmlObjectReference(MxmlObjectReference mxmlObjectReference) {
    assert this.mxmlObjectReference == null;
    this.mxmlObjectReference = mxmlObjectReference;
  }

  public int getOrAllocateId() {
    if (id == -1) {
      id = getParentScope().referenceCounter++;
      referenceInitialized();
    }

    return id;
  }

  public MxmlObjectReference getMxmlObjectReference() {
    if (mxmlObjectReference == null) {
      mxmlObjectReference = new MxmlObjectReference(getOrAllocateId());
    }

    return mxmlObjectReference;
  }
}