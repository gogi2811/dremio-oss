/*
 * Copyright (C) 2017-2019 Dremio Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import { PureComponent } from "react";
import { Link } from "react-router";
import Radium from "radium";

import invariant from "invariant";

import PropTypes from "prop-types";

import * as buttonStyles from "uiTheme/radium/buttons";

//TODO Remove radium
const RadiumLink = Radium(Link);

export default class LinkButton extends PureComponent {
  static propTypes = {
    buttonStyle: PropTypes.string,
    style: PropTypes.object,
    children: PropTypes.node,
  };

  static defaultProps = {
    buttonStyle: "secondary",
  };

  render() {
    const { buttonStyle, ...linkProps } = this.props;
    const { style, children } = linkProps;
    invariant(
      buttonStyles[buttonStyle] !== undefined,
      `Unknown button style: "${buttonStyle}"`
    );

    return (
      <RadiumLink
        {...linkProps}
        style={[styles.base, buttonStyles[buttonStyle], style]}
      >
        {children}
      </RadiumLink>
    );
  }
}

const styles = {
  base: {
    lineHeight: "27px",
    textDecoration: "none",
  },
};
