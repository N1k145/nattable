/*******************************************************************************
 * Copyright (c) 2012, 2020 Original authors and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Original authors and others - initial API and implementation
 ******************************************************************************/
package org.eclipse.nebula.widgets.nattable.columnRename;

import org.eclipse.nebula.widgets.nattable.command.AbstractLayerCommandHandler;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

public class DisplayColumnRenameDialogCommandHandler extends AbstractLayerCommandHandler<DisplayColumnRenameDialogCommand> {

    private final ColumnHeaderLayer columnHeaderLayer;

    public DisplayColumnRenameDialogCommandHandler(ColumnHeaderLayer columnHeaderLayer) {
        this.columnHeaderLayer = columnHeaderLayer;
    }

    @Override
    protected boolean doCommand(DisplayColumnRenameDialogCommand command) {
        int columnPosition = command.getColumnPosition();
        String originalLabel = this.columnHeaderLayer.getOriginalColumnLabel(columnPosition);
        String renamedLabel = this.columnHeaderLayer.getRenamedColumnLabel(columnPosition);

        ColumnRenameDialog dialog = new ColumnRenameDialog(
                Display.getDefault().getActiveShell(),
                originalLabel,
                renamedLabel);
        Rectangle colHeaderBounds = this.columnHeaderLayer.getBoundsByPosition(columnPosition, 0);
        Point point = new Point(colHeaderBounds.x, colHeaderBounds.y + colHeaderBounds.height);
        dialog.setLocation(command.toDisplayCoordinates(point));
        dialog.open();

        if (dialog.isCancelPressed()) {
            return true;
        }

        return this.columnHeaderLayer.doCommand(
                new RenameColumnHeaderCommand(
                        this.columnHeaderLayer,
                        columnPosition,
                        dialog.getNewColumnLabel()));
    }

    @Override
    public Class<DisplayColumnRenameDialogCommand> getCommandClass() {
        return DisplayColumnRenameDialogCommand.class;
    }

}
