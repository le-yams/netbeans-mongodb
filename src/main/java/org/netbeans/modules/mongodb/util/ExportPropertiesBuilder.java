/* 
 * Copyright (C) 2015 Yann D'Isanto
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.netbeans.modules.mongodb.util;

import java.io.File;
import java.nio.charset.Charset;
import org.bson.Document;

/**
 *
 * @author Yann D'Isanto
 */
public final class ExportPropertiesBuilder {
    
    private String collection;
    
    private Document criteria;

    private Document projection;

    private Document sort;
    
    private boolean jsonArray;

    private File file;
    
    private Charset encoding;

    public ExportPropertiesBuilder() {
        this(null);
    }

    public ExportPropertiesBuilder(String collection) {
        this.collection = collection;
    }
    
    public ExportPropertiesBuilder collection(String collection) {
        this.collection = collection;
        return this;
    }

    public ExportPropertiesBuilder criteria(Document criteria) {
        this.criteria = criteria;
        return this;
    }

    public ExportPropertiesBuilder projection(Document projection) {
        this.projection = projection;
        return this;
    }

    public ExportPropertiesBuilder sort(Document sort) {
        this.sort = sort;
        return this;
    }

    public ExportPropertiesBuilder jsonArray(boolean jsonArray) {
        this.jsonArray = jsonArray;
        return this;
    }
    
    public ExportPropertiesBuilder file(File file) {
        this.file = file;
        return this;
    }
    
    public ExportPropertiesBuilder encoding(Charset encoding) {
        this.encoding = encoding;
        return this;
    }
    
    public ExportProperties build() {
        return new ExportProperties(collection, criteria, projection, sort, jsonArray, file, encoding);
    }
}
