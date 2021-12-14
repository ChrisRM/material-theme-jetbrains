/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */
@file:Suppress("DialogTitleCapitalization")

package com.mallowigi.idea.annotators.settings

import com.intellij.icons.AllIcons
import com.intellij.lang.Language
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.CodeInsightColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.psi.codeStyle.DisplayPriority
import com.mallowigi.idea.annotators.KotlinAnnotator
import com.mallowigi.idea.messages.LanguageAdditionsBundle.message
import gnu.trove.THashMap
import java.util.Collections
import javax.swing.Icon

/**
 * Kotlin Additions color settings
 *
 */
class KotlinColorSettings : BaseColorSettings() {
  override fun getIcon(): Icon = AllIcons.Javaee.Home

  override fun getHighlighter(): SyntaxHighlighter {
    val lang = Language.findLanguageByID("kotlin") ?: Language.ANY
    return getSyntaxHighlighterWithFallback(lang)
  }

  override fun getDemoText(): String = message("KotlinColorPage.demoText")

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> =
    Collections.unmodifiableMap(KOTLIN_DESCRIPTORS)

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> = KOTLIN_ATTRIBUTES

  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

  override fun getDisplayName(): String = message("KotlinColorPage.kotlin.additions")

  override fun getPriority(): DisplayPriority = DisplayPriority.LANGUAGE_SETTINGS

  companion object {
    private val KOTLIN_ATTRIBUTES: Array<AttributesDescriptor>
    private val KOTLIN_DESCRIPTORS: MutableMap<String, TextAttributesKey> = THashMap()
    private val THIS_SUPER = KotlinAnnotator.THIS_SUPER
    private val MODIFIER = KotlinAnnotator.MODIFIER
    private val COMPANION = KotlinAnnotator.COMPANION
    private val DATA = KotlinAnnotator.DATA
    private val OP_INFIX = KotlinAnnotator.OP_INFIX
    private val NULL_UNIT = KotlinAnnotator.NULL_UNIT
    private val IMPORT_PACKAGE = KotlinAnnotator.IMPORT_PACKAGE
    private val SEALED_OVERRIDE = KotlinAnnotator.SEALED_OVERRIDE
    private val PRIMITIVE = KotlinAnnotator.PRIMITIVE

    init {
      KOTLIN_ATTRIBUTES = arrayOf(
        AttributesDescriptor(message("keywords.this.super"), THIS_SUPER),
        AttributesDescriptor(message("keywords.private.public.internal"), MODIFIER),
        AttributesDescriptor(message("keywords.companion"), COMPANION),
        AttributesDescriptor(message("keywords.null.unit"), NULL_UNIT),
        AttributesDescriptor(message("keywords.import.package"), IMPORT_PACKAGE),
        AttributesDescriptor(message("keywords.true.false"), PRIMITIVE),
        AttributesDescriptor(message("keywords.data"), DATA),
        AttributesDescriptor(message("keywords.sealed.override.open"), SEALED_OVERRIDE),
        AttributesDescriptor(message("keywords.operator.infix"), OP_INFIX)
      )
      KOTLIN_DESCRIPTORS.putAll(createAdditionalHlAttrs())
    }

    @Suppress("HardCodedStringLiteral")
    private fun createAdditionalHlAttrs(): Map<String, TextAttributesKey> {
      val descriptors: MutableMap<String, TextAttributesKey> = THashMap()
      descriptors["field"] = TextAttributesKey.find("INSTANCE_FIELD_ATTRIBUTES")
      descriptors["unusedField"] = CodeInsightColors.NOT_USED_ELEMENT_ATTRIBUTES
      descriptors["error"] = CodeInsightColors.ERRORS_ATTRIBUTES
      descriptors["warning"] = CodeInsightColors.WARNINGS_ATTRIBUTES
      descriptors["weak_warning"] = CodeInsightColors.WEAK_WARNING_ATTRIBUTES
      descriptors["server_problems"] = CodeInsightColors.GENERIC_SERVER_ERROR_OR_WARNING
      descriptors["server_duplicate"] = CodeInsightColors.DUPLICATE_FROM_SERVER
      descriptors["unknownType"] = CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES
      descriptors["localVar"] = TextAttributesKey.find("LOCAL_VARIABLE_ATTRIBUTES")
      descriptors["reassignedLocalVar"] = TextAttributesKey.find("REASSIGNED_LOCAL_VARIABLE_ATTRIBUTES")
      descriptors["reassignedParameter"] = TextAttributesKey.find("REASSIGNED_PARAMETER_ATTRIBUTES")
      descriptors["implicitAnonymousParameter"] =
        TextAttributesKey.find("IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES")
      descriptors["static"] = TextAttributesKey.find("STATIC_FIELD_ATTRIBUTES")
      descriptors["static_final"] = TextAttributesKey.find("STATIC_FINAL_FIELD_ATTRIBUTES")
      descriptors["deprecated"] = CodeInsightColors.DEPRECATED_ATTRIBUTES
      descriptors["for_removal"] = CodeInsightColors.MARKED_FOR_REMOVAL_ATTRIBUTES
      descriptors["constructorCall"] = TextAttributesKey.find("CONSTRUCTOR_CALL_ATTRIBUTES")
      descriptors["constructorDeclaration"] = TextAttributesKey.find("CONSTRUCTOR_DECLARATION_ATTRIBUTES")
      descriptors["methodCall"] = TextAttributesKey.find("METHOD_CALL_ATTRIBUTES")
      descriptors["methodDeclaration"] = TextAttributesKey.find("METHOD_DECLARATION_ATTRIBUTES")
      descriptors["static_method"] = TextAttributesKey.find("STATIC_METHOD_ATTRIBUTES")
      descriptors["abstract_method"] = TextAttributesKey.find("ABSTRACT_METHOD_ATTRIBUTES")
      descriptors["inherited_method"] = TextAttributesKey.find("INHERITED_METHOD_ATTRIBUTES")
      descriptors["param"] = TextAttributesKey.find("PARAMETER_ATTRIBUTES")
      descriptors["lambda_param"] = TextAttributesKey.find("LAMBDA_PARAMETER_ATTRIBUTES")
      descriptors["class"] = TextAttributesKey.find("CLASS_NAME_ATTRIBUTES")
      descriptors["anonymousClass"] = TextAttributesKey.find("ANONYMOUS_CLASS_NAME_ATTRIBUTES")
      descriptors["typeParameter"] = TextAttributesKey.find("TYPE_PARAMETER_NAME_ATTRIBUTES")
      descriptors["abstractClass"] = TextAttributesKey.find("ABSTRACT_CLASS_NAME_ATTRIBUTES")
      descriptors["interface"] = TextAttributesKey.find("INTERFACE_NAME_ATTRIBUTES")
      descriptors["enum"] = TextAttributesKey.find("ENUM_NAME_ATTRIBUTES")
      descriptors["annotationName"] = TextAttributesKey.find("ANNOTATION_NAME_ATTRIBUTES")
      descriptors["annotationAttributeName"] = TextAttributesKey.find("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES")
      descriptors["javadocTagValue"] = TextAttributesKey.find("DOC_COMMENT_TAG_VALUE")
      descriptors["instanceFinalField"] = TextAttributesKey.find("INSTANCE_FINAL_FIELD_ATTRIBUTES")
      descriptors["staticallyConstImported"] = TextAttributesKey.find("STATIC_FINAL_FIELD_IMPORTED_ATTRIBUTES")
      descriptors["staticallyImported"] = TextAttributesKey.find("STATIC_FIELD_IMPORTED_ATTRIBUTES")
      descriptors["static_imported_method"] = TextAttributesKey.find("STATIC_METHOD_CALL_IMPORTED_ATTRIBUTES")

      descriptors["string"] = DefaultLanguageHighlighterColors.STRING
      descriptors["number"] = DefaultLanguageHighlighterColors.NUMBER
      descriptors["val"] = DefaultLanguageHighlighterColors.KEYWORD
      descriptors["keyword"] = DefaultLanguageHighlighterColors.KEYWORD
      descriptors["this"] = THIS_SUPER
      descriptors["modifier"] = MODIFIER
      descriptors["companion"] = COMPANION
      descriptors["data"] = DATA
      descriptors["infix"] = OP_INFIX
      descriptors["null"] = NULL_UNIT
      descriptors["import"] = IMPORT_PACKAGE
      descriptors["sealed"] = SEALED_OVERRIDE
      descriptors["primitive"] = PRIMITIVE
      return descriptors
    }
  }
}
