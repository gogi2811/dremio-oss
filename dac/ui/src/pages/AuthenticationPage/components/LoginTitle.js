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
import { injectIntl } from "react-intl";

import PropTypes from "prop-types";

import Art from "components/Art";

const isBeta = process.env.DREMIO_BETA === "true";

class LoginTitle extends PureComponent {
  static propTypes = {
    subTitle: PropTypes.node,
    style: PropTypes.object,
    intl: PropTypes.object.isRequired,
  };

  render() {
    const { style } = this.props;
    return (
      <div id="login-title" style={{ ...styles.base, ...(style || {}) }}>
        <div style={styles.mainTitle}>
          <span className={"dremioLogoWithTextContainer"}>
            <Art
              src={
                isBeta
                  ? "Narwhal-Logo-With-Name-Light-Beta.svg"
                  : "NarwhalLogoWithNameLight.svg"
              }
              alt={this.props.intl.formatMessage({ id: "App.NarwhalLogo" })}
              style={styles.icon}
              className={"dremioLogoWithText"}
            />
          </span>
        </div>
        <h1 style={styles.subtitle}>{this.props.subTitle}</h1>
      </div>
    );
  }
}

const styles = {
  base: {
    marginBottom: 40,
  },
  subtitle: {
    color: "#43B8C9",
    fontSize: 27,
  },
  mainTitle: {
    display: "flex",
    alignItems: "center",
  },
  icon: {
    width: 223,
    height: 75,
    marginBottom: 4,
  },
};

export default injectIntl(LoginTitle);
