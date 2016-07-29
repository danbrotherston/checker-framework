package org.checkerframework.checker.gradualnullness;

import org.checkerframework.checker.nullness.NullnessAnnotatedTypeFactory;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.framework.qual.Dynamic;
import org.checkerframework.framework.qual.TypeUseLocation;
import org.checkerframework.framework.type.QualifierHierarchy;
import org.checkerframework.framework.util.defaults.QualifierDefaults;
import org.checkerframework.framework.util.MultiGraphQualifierHierarchy;
import org.checkerframework.framework.util.MultiGraphQualifierHierarchy.MultiGraphFactory;
import org.checkerframework.javacutil.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GradualNullnessAnnotatedTypeFactory extends NullnessAnnotatedTypeFactory {
    public GradualNullnessAnnotatedTypeFactory(BaseTypeChecker checker, boolean useFbc) {
	super(checker, useFbc);
    }

    @Override
    protected void addUncheckedCodeDefaults(QualifierDefaults defs) {
        //super.addUnannotatedDefaultsToQualifierDefaults(defs, unused);
	defs.addUncheckedCodeDefault(AnnotationUtils.fromClass(elements, Dynamic.class),
				     TypeUseLocation.RETURN);
	defs.addUncheckedCodeDefault(AnnotationUtils.fromClass(elements, Dynamic.class),
				     TypeUseLocation.PARAMETER);
	defs.addUncheckedCodeDefault(AnnotationUtils.fromClass(elements, Dynamic.class),
				     TypeUseLocation.UPPER_BOUND);
	//	defs.addUncheckedCodeDefault(AnnotationUtils.fromClass(elements, Dynamic.class),
	//			     TypeUseLocation.EXPLICIT_UPPER_BOUND);
	defs.addUncheckedCodeDefault(AnnotationUtils.fromClass(elements, Dynamic.class),
				     TypeUseLocation.FIELD);

	defs.addUncheckedStandardDefaults(
	     Arrays.asList(AnnotationUtils.fromClass(elements, Dynamic.class)),
	     Arrays.asList(AnnotationUtils.fromClass(elements, Dynamic.class)));

	//defs.treatAccessibleFieldsAsUnannotated();
	//	return unused;
        //super.addUncheckedCodeDefaults(defs);
    }

    @Override
    protected MultiGraphFactory createQualifierHierarchyFactory() {
	return new MultiGraphQualifierHierarchy.MultiGraphFactory(this);
    }

    @Override
    public QualifierHierarchy createQualifierHierarchy(MultiGraphFactory factory) {
	return new NullnessQualifierHierarchy(factory, (Object []) null);
    }

    @Override
    protected Set<Class<? extends Annotation>> createSupportedTypeQualifiers() {
	Set<Class<? extends Annotation>> gradualQualifiers =
	    new HashSet<Class<? extends Annotation>>(Arrays.asList(Dynamic.class));
	
	Set<Class<? extends Annotation>> nullnessQualifiers =
	    super.createSupportedTypeQualifiers();

	Set<Class<? extends Annotation>> typeQualifiers = new HashSet<Class<? extends Annotation>>();
	typeQualifiers.addAll(gradualQualifiers);
	typeQualifiers.addAll(nullnessQualifiers);
	return Collections.unmodifiableSet(typeQualifiers);
    }
}
