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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BMLObject {

    private String objectName;
    private List<BMLProperty> properties;
    private List<BMLObject> subObjects;

    public BMLObject(String objectName, List<BMLProperty> properties, List<BMLObject> subObjects) {
        this.objectName = objectName;
        this.properties = properties;
        this.subObjects = subObjects;
    }

    public String getObjectName() {
        return objectName;
    }

    public List<BMLProperty> getProperties() {
        return properties;
    }

    public List<BMLObject> getSubObjects() {
        return subObjects;
    }

    public Optional<BMLProperty> get(String propertyName) {
        return properties.stream().filter((p) -> p.getPropertyName().equalsIgnoreCase(propertyName)).findFirst();
    }

    public Optional<BMLObject> getObject(String objectName) {
        return subObjects.stream().filter((o) -> o.getObjectName().equalsIgnoreCase(objectName)).findFirst();
    }

    public void add(BMLProperty property) {
        properties.add(property);
    }

    public void add(BMLObject object) {
        subObjects.add(object);
    }

}
