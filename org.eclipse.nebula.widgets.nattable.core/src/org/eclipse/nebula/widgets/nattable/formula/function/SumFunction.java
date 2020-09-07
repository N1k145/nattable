/*****************************************************************************
 * Copyright (c) 2015, 2020 CEA LIST.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *		Dirk Fauth <dirk.fauth@googlemail.com> - Initial API and implementation
 *****************************************************************************/
package org.eclipse.nebula.widgets.nattable.formula.function;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * Returns the sum of a supplied list of numbers.
 *
 * @since 1.4
 */
public class SumFunction extends AbstractMathFunction {

    public SumFunction() {
        super();
    }

    public SumFunction(List<FunctionValue> values) {
        super(values);
    }

    @Override
    public BigDecimal getValue() {
        BigDecimal result = new BigDecimal(0);
        for (FunctionValue value : this.values) {
            result = result.add(convertValue(value.getValue()));
        }
        return result;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "+"; //$NON-NLS-1$
        } else if (this.values.size() == 1) {
            return "+ " + this.values.get(0); //$NON-NLS-1$
        } else {
            StringBuilder builder = new StringBuilder();
            for (Iterator<FunctionValue> it = this.values.iterator(); it.hasNext();) {
                FunctionValue v = it.next();
                builder.append(v);
                if (it.hasNext()) {
                    builder.append(" + "); //$NON-NLS-1$
                }
            }
            return builder.toString();
        }
    }
}
