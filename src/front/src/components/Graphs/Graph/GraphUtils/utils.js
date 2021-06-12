export const  getYRatio = (height, yMax, yMin) => {
	return (yMax - yMin) / height
}

export const  getXRatio = (width, length) => {
	return width / (length - 2)
}

export const toCoords = (y, i, xRatio, yRatio, DPI_HEIGHT, PADDING, yMin, offsetX) => {
	return [
		Math.floor((i - 1) * xRatio + offsetX),
		Math.floor(DPI_HEIGHT - PADDING - ((y - yMin) /  yRatio))
	];
}

export const renderLines = (ctx, yData, xRatio, yRatio, DPI_HEIGHT, PADDING, data, yMin, offsetX) => {
	const renderLine = (coords, color = '#000') => {
		ctx.beginPath();
		ctx.lineWidth = 4;
		ctx.strokeStyle = color;
		ctx.lineJoin = 'bevel';
		for (const [x, y] of coords) {
			ctx.lineTo(x, y);
		}
		ctx.stroke();
		ctx.closePath();
	}


	yData.forEach(line => {
		const coords = line.map((y, i) => toCoords(y, i, xRatio, yRatio, DPI_HEIGHT, PADDING, yMin, offsetX)).filter((_, i) => i !== 0);
		
		renderLine(coords, data.color[line[0]]);
	})
}