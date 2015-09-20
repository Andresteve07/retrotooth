/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrotooth;

import com.squareup.wire.Message;
import com.squareup.wire.Wire;

import java.io.IOException;

import okio.BufferedSource;

final class WireResponseBodyConverter<T extends Message> implements Converter<ResponseData, T> {
    private final Wire wire;
    private final Class<T> cls;

    WireResponseBodyConverter(Wire wire, Class<T> cls) {
        this.wire = wire;
        this.cls = cls;
    }

    @Override
    public T convert(ResponseData value) throws IOException {
        BufferedSource source = value.source();
        try {
            return wire.parseFrom(source, cls);
        } finally {
            Utils.closeQuietly(source);
        }
    }
}
