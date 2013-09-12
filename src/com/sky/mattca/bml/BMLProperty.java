/***
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Matthew William Carter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.sky.mattca.bml;

public class BMLProperty {

    public enum ValueType {
        STRING,
        INTEGER,
        DECIMAL,
        VECTOR_2,
        VECTOR_3,
    }

    private String propertyName;
    private ValueType propertyType;

    private String[] contents;

    public BMLProperty(String propertyName, String value) {
        this.propertyName = propertyName;
        this.propertyType = ValueType.STRING;
        this.contents = new String[] {value};
    }

    public BMLProperty(String propertyName, int value) {
        this.propertyName = propertyName;
        this.propertyType = ValueType.INTEGER;
        this.contents = new String[] { Integer.toString(value) };
    }

    public BMLProperty(String propertyName, float value) {
        this.propertyName = propertyName;
        this.propertyType = ValueType.DECIMAL;
        this.contents = new String[] { Float.toString(value) };
    }

    public BMLProperty(String propertyName, float item1, float item2) {
        this.propertyName = propertyName;
        this.propertyType = ValueType.VECTOR_2;
        this.contents = new String[] { Float.toString(item1), Float.toString(item2) };
    }

    public BMLProperty(String propertyName, float item1, float item2, float item3) {
        this.propertyName = propertyName;
        this.propertyType = ValueType.VECTOR_3;
        this.contents = new String[] { Float.toString(item1), Float.toString(item2), Float.toString(item3) };
    }

    private void checkTypes(ValueType desiredType) throws Exception {
        if (propertyType == desiredType) {
            return;
        } else {
            throw new Exception("Cannot convert " + propertyType.name() + " to " + desiredType.name() + ".");
        }
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String asString() throws Exception {
        checkTypes(ValueType.STRING);
        return contents[0];
    }

    public int asInteger() throws Exception {
        checkTypes(ValueType.INTEGER);
        return Integer.parseInt(contents[0]);
    }

    public float asFloat() throws Exception {
        checkTypes(ValueType.DECIMAL);
        return Float.parseFloat(contents[0]);
    }

    public float[] asVector2() throws Exception {
        checkTypes(ValueType.VECTOR_2);
        return new float[] { Float.parseFloat(contents[0]), Float.parseFloat(contents[1]) };
    }

    public float[] asVector3() throws Exception {
        checkTypes(ValueType.VECTOR_3);
        return new float[] { Float.parseFloat(contents[0]), Float.parseFloat(contents[1]), Float.parseFloat(contents[2]) };
    }

}
