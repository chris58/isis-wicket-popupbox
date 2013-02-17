/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package com.marintek.isis.wicket.ui.components.scalars.wicket;

import com.marintek.isis.wicket.popupbox.applib.PopupWicketBox;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.ValueModel;
import org.apache.isis.viewer.wicket.ui.panels.PanelAbstract;
import org.apache.wicket.core.util.resource.UrlResourceStream;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;

public class StandaloneValueAsPopupWicketBox extends PanelAbstract<ValueModel> {

    private static final long serialVersionUID = 1L;

    public StandaloneValueAsPopupWicketBox(final String id, final ValueModel valueModel) {
        super(id, valueModel);

        buildGui();
    }

    private void buildGui() {
        PopupWicketBox fb;
        IModel fbModel;

        final ValueModel model = getModel();
        final ObjectAdapter boxAdapter = model.getObject();
        final Object boxObj = boxAdapter.getObject();
        PopupWicketBox box = (PopupWicketBox) boxObj;

        // Add that link as a popup
        PopupSettings popupSettings = new PopupSettings(PopupSettings.RESIZABLE | PopupSettings.SCROLLBARS);
        popupSettings.setHeight(box.getHeight());
        popupSettings.setWidth(box.getWidth());
        popupSettings.setLeft(box.getLeft());
        popupSettings.setTop(box.getTop());
        popupSettings.setWindowName(box.getTitle());
        
        ExternalLink link = new ExternalLink("popupbox", box.getUrl(),
                "Pop Up").setPopupSettings(popupSettings);
        addOrReplace(link);
    }

    
    // for static html
    public class URLResourcePanel extends WebComponent {

        //private static final Logger	log	= Logger.getLogger(URLResourcePanel.class);
        private final IModel noResourceMessageModel;
        private final IModel urlContainingModel;

        /**
         * @param id
         * @param urlContainingModel the url to replace the body of this panel
         * with.
         * @param noResourceMessageModel contains the string to display if the
         * url cannot be resolved or doesn't load properly.
         *
         */
        public URLResourcePanel(String id, IModel urlContainingModel, IModel noResourceMessageModel) {
            super(id);
            this.urlContainingModel = urlContainingModel;
            this.noResourceMessageModel = noResourceMessageModel;
        }

        /* (non-Javadoc)
         * @see org.apache.wicket.Component#onComponentTagBody(org.apache.wicket.markup.MarkupStream, org.apache.wicket.markup.ComponentTag)
         */
        /**
         *
         * @param markupStream
         * @param openTag
         */
        @Override
        protected final void onComponentTag(
                ComponentTag openTag) {

            try {
                String body = getBody();
                getResponse().write(body);
                return;
            } catch (Exception e) {
                // fall through
            }
            getResponse().write(noResourceMessageModel.getObject().toString());
        }

        /* load the url content as the body of the component */
        private String getBody() throws IOException, ResourceStreamNotFoundException {
            String url = (String) this.urlContainingModel.getObject();
            UrlResourceStream resourceStream = new UrlResourceStream(new URL(url));
            //read it with BufferedReader
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(resourceStream.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
    }

    @Override
    protected void onModelChanged() {
        buildGui();
    }
}
