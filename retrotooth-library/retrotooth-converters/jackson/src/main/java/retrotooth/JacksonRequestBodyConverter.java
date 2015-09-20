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

import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

import retrotooth.ok.MediaType;

final class JacksonRequestBodyConverter<T> implements Converter<T, RequestData> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private final ObjectWriter adapter;

    JacksonRequestBodyConverter(ObjectWriter adapter) {
        this.adapter = adapter;
    }

    @Override
    public RequestData convert(T value) throws IOException {
        byte[] bytes = adapter.writeValueAsBytes(value);
        return RequestData.create(MEDIA_TYPE, bytes);
    }
}