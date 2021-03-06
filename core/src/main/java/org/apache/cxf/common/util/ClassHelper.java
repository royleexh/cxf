/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.cxf.common.util;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;

/**
 * 
 */
public class ClassHelper {
    static final ClassHelper HELPER;
    static {
        ClassHelper theHelper = null;
        try {
            theHelper = new SpringAopClassHelper();
        } catch (Throwable ex) {
            theHelper = new ClassHelper();
        }
        HELPER = theHelper;
    }
    
    
    protected ClassHelper() {
    }
    
    protected Class<?> getRealClassInternal(Object o) {
        return o.getClass();
    }
    
    protected Class<?> getRealClassFromClassInternal(Class<?> cls) {
        return cls;
    }
    protected Object getRealObjectInternal(Object o) {
        return o;
    }
    
    public static Class<?> getRealClass(Object o) {
        return getRealClass(null, o);
    }
    
    public static Class<?> getRealClassFromClass(Class<?> cls) {
        return HELPER.getRealClassFromClassInternal(cls);
    }
    
    public static Object getRealObject(Object o) {
        return HELPER.getRealObjectInternal(o);
    }

    public static Class<?> getRealClass(Bus bus, Object o) {
        bus = bus == null ? BusFactory.getThreadDefaultBus() : bus;
        if (bus != null && bus.getProperty(ClassUnwrapper.class.getName()) != null) {
            ClassUnwrapper unwrapper = (ClassUnwrapper)bus.getProperty(ClassUnwrapper.class.getName());
            return unwrapper.getRealClass(o);
        } else {
            return HELPER.getRealClassInternal(o);
        }
    }
    
}
