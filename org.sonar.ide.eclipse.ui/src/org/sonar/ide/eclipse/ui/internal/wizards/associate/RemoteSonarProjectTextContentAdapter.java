/*
 * Sonar Eclipse
 * Copyright (C) 2010-2013 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.ide.eclipse.ui.internal.wizards.associate;

import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.widgets.Control;

/**
 * This adapter will update the model ({@link ProjectAssociationModel}) of a row
 * with what is provided by the content assist. Because content assist can only return
 * a String there is a serialization that is done with {@link RemoteSonarProject#asString()}
 * and here we can deserialize using {@link RemoteSonarProject#fromString(String)}
 * @author julien
 *
 */
public class RemoteSonarProjectTextContentAdapter extends TextContentAdapter {
  private ProjectAssociationModel sonarProject;

  public RemoteSonarProjectTextContentAdapter(ProjectAssociationModel sonarProject) {
    this.sonarProject = sonarProject;
  }

  @Override
  public void insertControlContents(Control control, String text, int cursorPosition) {
    RemoteSonarProject prj = RemoteSonarProject.fromString(text);
    sonarProject.associate(prj.getUrl(), prj.getName(), prj.getKey());
    // Don't insert but instead replace
    super.setControlContents(control, prj.getName(), cursorPosition);
  }

  @Override
  public void setControlContents(Control control, String text, int cursorPosition) {
    RemoteSonarProject prj = RemoteSonarProject.fromString(text);
    sonarProject.associate(prj.getUrl(), prj.getName(), prj.getKey());
    super.setControlContents(control, prj.getName(), cursorPosition);
  }

}
