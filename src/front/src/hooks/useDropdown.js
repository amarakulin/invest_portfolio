import { useState, useEffect } from 'react';

export const useDropdown = (ref) => {
	const [isOpen, toggleIsOpen] = useState(false);

	const observeClick = (e) => {
		if (ref.current && !ref.current.contains(e.target)) {
			toggleIsOpen(false);
		}
	}

	useEffect(() => {
		document.addEventListener('click', observeClick);
		return () => {
			document.removeEventListener('click', observeClick);
		}
	}, [ref])

	return [isOpen, toggleIsOpen];
}