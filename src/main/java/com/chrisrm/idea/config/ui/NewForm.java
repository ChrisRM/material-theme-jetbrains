/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

/*
 * Created by JFormDesigner on Fri Jan 26 22:52:40 IST 2018
 */

package com.chrisrm.idea.config.ui;

import com.intellij.openapi.ui.ComboBox;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 * @author Elior Boukhobza
 */
public class NewForm extends JPanel {
  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JFormattedTextField formattedTextField1;
  private JScrollPane scrollPane3;
  private JList list1;
  private JSpinner spinner1;
  private JToggleButton toggleButton1;
  private JComboBox comboBox1;
  private JScrollPane scrollPane2;
  private JEditorPane editorPane1;
  private JScrollPane scrollPane4;
  private JTable table1;
  private JRadioButton radioButton1;
  private JScrollBar scrollBar1;
  private JToggleButton toggleButton2;
  private JSlider slider1;
  private JProgressBar progressBar1;
  private JScrollPane scrollPane1;
  private JTextArea textArea1;
  private JScrollPane scrollPane5;
  private JTree tree1;
  private ComboBox comboBox2;

  public NewForm() {
    initComponents();
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    formattedTextField1 = new JFormattedTextField();
    scrollPane3 = new JScrollPane();
    list1 = new JList();
    spinner1 = new JSpinner();
    toggleButton1 = new JToggleButton();
    comboBox1 = new JComboBox();
    scrollPane2 = new JScrollPane();
    editorPane1 = new JEditorPane();
    scrollPane4 = new JScrollPane();
    table1 = new JTable();
    radioButton1 = new JRadioButton();
    scrollBar1 = new JScrollBar();
    toggleButton2 = new JToggleButton();
    slider1 = new JSlider();
    progressBar1 = new JProgressBar();
    scrollPane1 = new JScrollPane();
    textArea1 = new JTextArea();
    scrollPane5 = new JScrollPane();
    tree1 = new JTree();
    comboBox2 = new ComboBox();

    //======== this ========
    setLayout(new MigLayout(
        "hidemode 3",
        // columns
        "[fill]" +
            "[358,fill]" +
            "[156,fill]",
        // rows
        "[]" +
            "[]" +
            "[172]" +
            "[119]" +
            "[308]"));
    add(formattedTextField1, "cell 0 0");

    //======== scrollPane3 ========
    {
      scrollPane3.setViewportView(list1);
    }
    add(scrollPane3, "cell 1 0");
    add(spinner1, "cell 2 0");

    //---- toggleButton1 ----
    toggleButton1.setText("text");
    add(toggleButton1, "cell 0 1");
    add(comboBox1, "cell 2 1");

    //======== scrollPane2 ========
    {
      scrollPane2.setViewportView(editorPane1);
    }
    add(scrollPane2, "cell 0 2");

    //======== scrollPane4 ========
    {
      scrollPane4.setViewportView(table1);
    }
    add(scrollPane4, "cell 1 2");

    //---- radioButton1 ----
    radioButton1.setText("text");
    add(radioButton1, "cell 2 2");
    add(scrollBar1, "cell 0 3");

    //---- toggleButton2 ----
    toggleButton2.setText("text");
    add(toggleButton2, "cell 0 3");
    add(slider1, "cell 1 3");
    add(progressBar1, "cell 2 3");

    //======== scrollPane1 ========
    {
      scrollPane1.setViewportView(textArea1);
    }
    add(scrollPane1, "cell 0 4");

    //======== scrollPane5 ========
    {
      scrollPane5.setViewportView(tree1);
    }
    add(scrollPane5, "cell 1 4");
    add(comboBox2, "cell 2 4");
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
