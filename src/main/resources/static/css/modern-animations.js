// Modern animations and interactions for Course Manager

document.addEventListener('DOMContentLoaded', function() {
    // Initialize all animations
    initCounterAnimations();
    initScrollEffects();
    initParticleEffect();
    initTypingEffect();
    initFloatingShapes();
});

// Animated counter for statistics
function initCounterAnimations() {
    const counters = document.querySelectorAll('.stat-number');

    const animateCounter = (counter) => {
        const target = parseInt(counter.getAttribute('data-count'));
        const duration = 2000; // 2 seconds
        const step = target / (duration / 16); // 60fps
        let current = 0;

        const timer = setInterval(() => {
            current += step;
            if (current >= target) {
                counter.textContent = target;
                clearInterval(timer);
            } else {
                counter.textContent = Math.floor(current);
            }
        }, 16);
    };

    // Intersection Observer for counter animation
    const counterObserver = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const counter = entry.target;
                if (!counter.classList.contains('animated')) {
                    counter.classList.add('animated');
                    animateCounter(counter);
                }
            }
        });
    });

    counters.forEach(counter => {
        counterObserver.observe(counter);
    });
}

// Scroll effects for navbar and elements
function initScrollEffects() {
    const navbar = document.querySelector('.modern-nav');

    window.addEventListener('scroll', () => {
        if (window.scrollY > 50) {
            navbar.style.background = 'rgba(15, 23, 42, 0.98)';
            navbar.style.backdropFilter = 'blur(25px)';
            navbar.style.boxShadow = '0 10px 30px rgba(0, 0, 0, 0.1)';
        } else {
            navbar.style.background = 'rgba(15, 23, 42, 0.95)';
            navbar.style.backdropFilter = 'blur(20px)';
            navbar.style.boxShadow = 'none';
        }
    });

    // Parallax effect for floating shapes
    const shapes = document.querySelectorAll('.shape');
    window.addEventListener('scroll', () => {
        const scrolled = window.pageYOffset;
        const rate = scrolled * -0.5;

        shapes.forEach((shape, index) => {
            const speed = (index + 1) * 0.3;
            shape.style.transform = `translateY(${rate * speed}px) rotate(${scrolled * 0.1}deg)`;
        });
    });
}

// Particle effect for hero background
function initParticleEffect() {
    const heroSection = document.querySelector('.hero-section');
    const particleContainer = document.createElement('div');
    particleContainer.className = 'particle-container';
    particleContainer.style.cssText = `
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        pointer-events: none;
        z-index: -1;
    `;

    heroSection.appendChild(particleContainer);

    // Create particles
    for (let i = 0; i < 50; i++) {
        createParticle(particleContainer);
    }
}

function createParticle(container) {
    const particle = document.createElement('div');
    const size = Math.random() * 4 + 2;
    const x = Math.random() * 100;
    const y = Math.random() * 100;
    const duration = Math.random() * 20 + 10;

    particle.style.cssText = `
        position: absolute;
        width: ${size}px;
        height: ${size}px;
        background: rgba(255, 255, 255, ${Math.random() * 0.5 + 0.1});
        border-radius: 50%;
        left: ${x}%;
        top: ${y}%;
        animation: float-particle ${duration}s linear infinite;
    `;

    container.appendChild(particle);

    // Remove particle after animation and create new one
    setTimeout(() => {
        particle.remove();
        createParticle(container);
    }, duration * 1000);
}

// Add CSS for particle animation
const particleStyle = document.createElement('style');
particleStyle.textContent = `
    @keyframes float-particle {
        0% {
            transform: translateY(100vh) rotate(0deg);
            opacity: 0;
        }
        10% {
            opacity: 1;
        }
        90% {
            opacity: 1;
        }
        100% {
            transform: translateY(-100px) rotate(360deg);
            opacity: 0;
        }
    }
`;
document.head.appendChild(particleStyle);

// Typing effect for hero title
function initTypingEffect() {
    const heroTitle = document.querySelector('.hero-title');
    if (!heroTitle) return;

    const originalText = heroTitle.innerHTML;
    heroTitle.innerHTML = '';

    let i = 0;
    const typeText = () => {
        if (i < originalText.length) {
            heroTitle.innerHTML += originalText.charAt(i);
            i++;
            setTimeout(typeText, 50);
        }
    };

    // Start typing effect after a delay
    setTimeout(typeText, 1000);
}

// Enhanced floating animations for shapes
function initFloatingShapes() {
    const shapes = document.querySelectorAll('.shape');

    shapes.forEach((shape, index) => {
        // Random floating animation
        const randomDelay = Math.random() * 2;
        const randomDuration = 4 + Math.random() * 4;

        shape.style.animationDelay = `${randomDelay}s`;
        shape.style.animationDuration = `${randomDuration}s`;

        // Add mouse interaction
        shape.addEventListener('mouseenter', () => {
            shape.style.transform = 'scale(1.2)';
            shape.style.background = 'rgba(255, 255, 255, 0.3)';
        });

        shape.addEventListener('mouseleave', () => {
            shape.style.transform = 'scale(1)';
            shape.style.background = 'rgba(255, 255, 255, 0.1)';
        });
    });
}

// Smooth scrolling for navigation links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});

// Button hover effects
document.querySelectorAll('.btn').forEach(button => {
    button.addEventListener('mouseenter', function() {
        this.style.transform = 'translateY(-2px)';
    });

    button.addEventListener('mouseleave', function() {
        this.style.transform = 'translateY(0)';
    });
});

// Feature card interactions
document.querySelectorAll('.feature-card').forEach(card => {
    card.addEventListener('mouseenter', function() {
        this.style.transform = 'translateY(-10px) scale(1.02)';
    });

    card.addEventListener('mouseleave', function() {
        this.style.transform = 'translateY(0) scale(1)';
    });
});

// Glitch effect for brand text (optional fun animation)
function addGlitchEffect() {
    const brand = document.querySelector('.navbar-brand');
    if (!brand) return;

    brand.addEventListener('mouseenter', function() {
        this.classList.add('glitch');
        setTimeout(() => {
            this.classList.remove('glitch');
        }, 500);
    });
}

// Add glitch CSS
const glitchStyle = document.createElement('style');
glitchStyle.textContent = `
    .glitch {
        position: relative;
        animation: glitch 0.3s ease-in-out;
    }
    
    .glitch::before,
    .glitch::after {
        content: attr(data-text);
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
    }
    
    .glitch::before {
        animation: glitch-1 0.3s ease-in-out;
        color: #ff0040;
        z-index: -1;
    }
    
    .glitch::after {
        animation: glitch-2 0.3s ease-in-out;
        color: #00ffff;
        z-index: -2;
    }
    
    @keyframes glitch {
        0%, 100% { transform: translate(0); }
        20% { transform: translate(-2px, 2px); }
        40% { transform: translate(-2px, -2px); }
        60% { transform: translate(2px, 2px); }
        80% { transform: translate(2px, -2px); }
    }
    
    @keyframes glitch-1 {
        0%, 100% { transform: translate(0); }
        20% { transform: translate(2px, -2px); }
        40% { transform: translate(-2px, 2px); }
        60% { transform: translate(-2px, -2px); }
        80% { transform: translate(2px, 2px); }
    }
    
    @keyframes glitch-2 {
        0%, 100% { transform: translate(0); }
        20% { transform: translate(-2px, -2px); }
        40% { transform: translate(2px, -2px); }
        60% { transform: translate(2px, 2px); }
        80% { transform: translate(-2px, 2px); }
    }
`;
document.head.appendChild(glitchStyle);

// Initialize glitch effect
addGlitchEffect();

// Loading animation for page
window.addEventListener('load', function() {
    document.body.classList.add('loaded');

    // Animate elements on load
    const elementsToAnimate = document.querySelectorAll('.hero-content, .dashboard-preview');
    elementsToAnimate.forEach((element, index) => {
        setTimeout(() => {
            element.style.opacity = '1';
            element.style.transform = 'translateY(0)';
        }, index * 200);
    });
});

// Add initial styles for load animation
const loadStyle = document.createElement('style');
loadStyle.textContent = `
    .hero-content,
    .dashboard-preview {
        opacity: 0;
        transform: translateY(30px);
        transition: all 0.8s ease;
    }
    
    body.loaded .hero-content,
    body.loaded .dashboard-preview {
        opacity: 1;
        transform: translateY(0);
    }
`;
document.head.appendChild(loadStyle);
