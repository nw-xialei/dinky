/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.dinky.function.compiler;

import org.dinky.function.data.model.UDF;

import org.apache.flink.configuration.ReadableConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * scala编译
 *
 * @since 0.6.8
 */
@Slf4j
public class ScalaCompiler implements FunctionCompiler {

    @Override
    public boolean compiler(UDF udf, ReadableConfig conf, Integer taskId) {
        // TODO 改为ProcessStep注释

        String className = udf.getClassName();
        log.info("正在编译 scala 代码 , class: " + className);
        if (CustomStringScalaCompiler.getInterpreter().compileString(udf.getCode())) {
            log.info("scala class编译成功:" + className);
            return true;
        } else {
            log.error("scala class编译失败:" + className);
            return false;
        }
    }
}
