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
import org.apache.isis.core.metamodel.spec.ObjectSpecification;
import org.apache.isis.core.metamodel.spec.SpecificationLoader;
import org.apache.isis.core.runtime.system.context.IsisContext;
import org.apache.isis.viewer.wicket.model.models.ValueModel;
import org.apache.isis.viewer.wicket.ui.ComponentFactory;
import org.apache.isis.viewer.wicket.ui.ComponentFactoryAbstract;
import org.apache.isis.viewer.wicket.ui.ComponentType;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * {@link ComponentFactory} for {@link StandaloneValueAsPopupWicketBox}.
 */          
public class StandaloneValueAsPopupWicketBoxFactory extends ComponentFactoryAbstract {

    private static final long serialVersionUID = 1L;

    public StandaloneValueAsPopupWicketBoxFactory() {
        super(ComponentType.VALUE);
    }

    @Override
    public ApplicationAdvice appliesTo(final IModel<?> model) {
        
        if (!(model instanceof ValueModel)) {
            return ApplicationAdvice.DOES_NOT_APPLY;
        }
        final ValueModel valueModel = (ValueModel) model;
        if(model.getObject() == null) {
            return ApplicationAdvice.DOES_NOT_APPLY;
        }
        
        final ObjectSpecification chartOptionsSpec = getSpecificationLoader().loadSpecification(PopupWicketBox.class);
        final ObjectSpecification scalarSpec = valueModel.getObject().getSpecification();
        
        return appliesExclusivelyIf(scalarSpec.isOfType(chartOptionsSpec));
    }

    @Override
    public Component createComponent(final String id, final IModel<?> scalarModel) {
        return new StandaloneValueAsPopupWicketBox(id, (ValueModel)scalarModel);
    }

    protected SpecificationLoader getSpecificationLoader() {
        return IsisContext.getSpecificationLoader();
    }
}
