function getImagePath(category) {
	var image_path;
	switch (category) {
		case "MSN":
			image_path = "../../../component/images/smiley/msn/"
			break;
		case "QQ":
			image_path = "../../../component/images/smiley/qq/";
			break;
	}
	return image_path;
}
/** get images */
function getImages(category) {
	var images;
	switch (category) {
		case "MSN":
			images = ['regular_smile.gif',
					  'sad_smile.gif',
					  'wink_smile.gif',
					  'teeth_smile.gif',
					  'confused_smile.gif',
					  'tounge_smile.gif',
					  'embaressed_smile.gif',
					  'omg_smile.gif',
					  'whatchutalkingabout_smile.gif',
					  'angry_smile.gif',
					  'angel_smile.gif',
					  'shades_smile.gif',
					  'devil_smile.gif',
					  'cry_smile.gif',
					  'lightbulb.gif',
					  'thumbs_down.gif',
					  'thumbs_up.gif',
					  'heart.gif',
					  'broken_heart.gif',
					  'kiss.gif',
					  'envelope.gif',
					  'cake.gif',
					  'gift.gif',
					  'rose.gif',
					  'wizen_rose.gif',
					  'sun.gif',
					  'star.gif',
					  'rainbow.gif',
					  'music.gif',
					  'coffee.gif'
					  ];
			break;
		case "QQ":
			break;
	}
	return images;
}