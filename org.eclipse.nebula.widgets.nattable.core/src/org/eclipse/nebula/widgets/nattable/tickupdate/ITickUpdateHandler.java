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
package org.eclipse.nebula.widgets.nattable.tickupdate;

/**
 * Interface for handler that is responsible for handling tick updates. Will
 * return information about which type of objects it can handle and will do the
 * increment/decrement calculation. Also specifies the default implementation
 * that can handle {@link Number} values.
 */
public interface ITickUpdateHandler {

    /**
     * Checks if the given object can be handled by this handler. Usually it
     * will simply perform a instanceof check.
     *
     * @param value
     *            The value to check.
     * @return <code>true</code> if this handler is able to perform tick updates
     *         on the given value, <code>false</code> if not.
     */
    boolean isApplicableFor(Object value);

    /**
     * Perform an increment of the current value by a default value.
     *
     * @param currentValue
     *            The value to perform the increment on.
     * @return The new value after increment it by a default value.
     */
    public Object getIncrementedValue(Object currentValue);

    /**
     * Perform an increment of the current value by the given increment value.
     *
     * @param currentValue
     *            The value to perform the increment on.
     * @param incrementSize
     *            The value the currentValue should be incremented by.
     * @return The new value after increment it by the specified value.
     */
    public Object getIncrementedValue(Object currentValue, double incrementSize);

    /**
     * Perform an decrement of the current value by a default value.
     *
     * @param currentValue
     *            The value to perform the decrement on.
     * @return The new value after decrement it by a default value.
     */
    public Object getDecrementedValue(Object currentValue);

    /**
     * Perform an decrement of the current value by the given decrement value.
     *
     * @param currentValue
     *            The value to perform the decrement on.
     * @param decrementSize
     *            The value the currentValue should be decremented by.
     * @return The new value after decrement it by the specified value.
     */
    public Object getDecrementedValue(Object currentValue, double decrementSize);

    /**
     * The default implementation of {@link ITickUpdateHandler} that handles
     * {@link Byte}, {@link Short}, {@link Integer}, {@link Long},
     * {@link Double} and {@link Float} values.
     */
    ITickUpdateHandler DEFAULT_TICK_UPDATE_HANDLER = new ITickUpdateHandler() {

        @Override
        public boolean isApplicableFor(Object value) {
            return (value instanceof Byte || value instanceof Short
                    || value instanceof Integer || value instanceof Long
                    || value instanceof Double || value instanceof Float);
        }

        @Override
        public Object getIncrementedValue(Object currentValue) {
            return getIncrementedValue(currentValue, 1);
        }

        @Override
        public Object getIncrementedValue(Object currentValue, double incrementSize) {
            if (currentValue instanceof Byte) {
                return (byte) (((Byte) currentValue) + (byte) Math.abs(incrementSize));
            }
            if (currentValue instanceof Short) {
                return (short) (((Short) currentValue) + (short) Math.abs(incrementSize));
            }
            if (currentValue instanceof Integer) {
                return Integer.valueOf(((Integer) currentValue) + (int) Math.abs(incrementSize));
            }
            if (currentValue instanceof Long) {
                return Long.valueOf(((Long) currentValue) + (long) Math.abs(incrementSize));
            }
            if (currentValue instanceof Double) {
                return Double.valueOf(((Double) currentValue) + Math.abs(incrementSize));
            }
            if (currentValue instanceof Float) {
                return Float.valueOf(((Float) currentValue) + (float) Math.abs(incrementSize));
            }
            return currentValue;
        }

        @Override
        public Object getDecrementedValue(Object currentValue) {
            return getDecrementedValue(currentValue, 1);
        }

        @Override
        public Object getDecrementedValue(Object currentValue, double decrementSize) {
            if (currentValue instanceof Byte) {
                return (byte) (((Byte) currentValue) - (byte) Math.abs(decrementSize));
            }
            if (currentValue instanceof Short) {
                return (short) (((Short) currentValue) - (short) Math.abs(decrementSize));
            }
            if (currentValue instanceof Integer) {
                return Integer.valueOf(((Integer) currentValue) - (int) Math.abs(decrementSize));
            }
            if (currentValue instanceof Long) {
                return Long.valueOf(((Long) currentValue) - (long) Math.abs(decrementSize));
            }
            if (currentValue instanceof Double) {
                return Double.valueOf(((Double) currentValue) - Math.abs(decrementSize));
            }
            if (currentValue instanceof Float) {
                return Float.valueOf(((Float) currentValue) - (float) Math.abs(decrementSize));
            }
            return currentValue;
        }

    };

}
