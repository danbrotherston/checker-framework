package checkers.regex.quals;

import java.lang.annotation.*;

import com.sun.source.tree.Tree;

import checkers.quals.*;

/**
 * Indicates that a {@code String} is a valid regular expression.
 */
@Documented
@TypeQualifier
@Inherited
@ImplicitFor(trees={Tree.Kind.NULL_LITERAL})
@SubtypeOf(Unqualified.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface Regex {}