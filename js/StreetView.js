//
//  StreetView.js
//  react-native-streetview
//
//  Created by Amit Palomo on 26/04/2017.
//  Copyright © 2017 Nester.co.il.
//

import React from 'react';
import {View, requireNativeComponent} from 'react-native';

const propTypes = {
  ...View.propTypes,

  // Center point
  coordinate: React.PropTypes.shape({latitude: React.PropTypes.number.isRequired, longitude: React.PropTypes.number.isRequired}),

  // Allowing user gestures (panning, zooming)
  allGesturesEnabled: React.PropTypes.bool,
  onLocationChanged: React.PropTypes.func
};

class StreetView extends React.Component {

  constructor(props) {
    super(props);
    this._onChange = this._onChange.bind(this);
  }

  _onChange(event) {
    if(!this.props.onLocationChanged) {
      return;
    }
    this.props.onLocationChanged(event.nativeEvent.invalid);
  }

  render() {
    return <NSTStreetView {...this.props}  onChange={this._onChange}  />;
  }
}

StreetView.propTypes = propTypes;

module.exports = requireNativeComponent('NSTStreetView', StreetView, {
  nativeOnly: {onChange: true}
});
