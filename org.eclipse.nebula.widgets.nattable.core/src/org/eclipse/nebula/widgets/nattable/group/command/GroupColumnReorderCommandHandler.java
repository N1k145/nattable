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
 *     Dirk Fauth <dirk.fauth@googlemail.com> - Bug 460052
 ******************************************************************************/
package org.eclipse.nebula.widgets.nattable.group.command;

import org.eclipse.nebula.widgets.nattable.command.AbstractLayerCommandHandler;
import org.eclipse.nebula.widgets.nattable.group.ColumnGroupReorderLayer;
import org.eclipse.nebula.widgets.nattable.reorder.command.ColumnReorderCommand;

/**
 * Handles updating of the Column Group Model when a column belonging to a group
 * is reordered. The actual reordering of the column is delegated to the lower
 * layers.
 */
public class GroupColumnReorderCommandHandler extends AbstractLayerCommandHandler<ColumnReorderCommand> {

    private final ColumnGroupReorderLayer columnGroupReorderLayer;

    public GroupColumnReorderCommandHandler(ColumnGroupReorderLayer columnGroupReorderLayer) {
        this.columnGroupReorderLayer = columnGroupReorderLayer;
    }

    @Override
    public Class<ColumnReorderCommand> getCommandClass() {
        return ColumnReorderCommand.class;
    }

    @Override
    protected boolean doCommand(ColumnReorderCommand command) {
        int fromColumnPosition = command.getFromColumnPosition();
        int toColumnPosition = command.getToColumnPosition();
        boolean reorderToLeftEdge = command.isReorderToLeftEdge();

        return this.columnGroupReorderLayer.updateColumnGroupModel(
                fromColumnPosition, toColumnPosition, reorderToLeftEdge);
    }
}
